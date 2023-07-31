import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ListView;


/**
 * The VendingMachineInterface class is responsible for displaying the user interface of the vending machine
 * and handling user interactions.
 * This class allows users to select products from the vending machine, enter cash, and receive change.
 * It interacts with the VendingMachine and VendingMachineController classes to perform these actions.
 * The VendingMachineInterface class serves as an intermediary between the user and the backend functionality of the vending machine.
 * 
 * <p>
 * The VendingMachineInterface class relies on the following classes:
 * - {@link Slots}: Represents the slots of the vending machine.
 * - {@link VendingMachineController}: Manages the business logic and calculations for the vending machine.
 * - {@link Product}: Represents a product that can be placed in the vending machine.
 * - {@link ProductSet}: Represents a set of products that can be combined and placed in the vending machine.
 * - {@link Cash}: Utility class for parsing cash input from the user.
 * - {@link CashBundle}: Represents the amount of each denomination of cash.
 * - {@link VendingMachineRequest}: Represents a user's request to purchase a product and enter cash.
 * </p>
 * 
 * @author Ceriola, Antonio
 */
public class VendingMachineInterface {
    
    private Slots slots;
    private VendingMachineController controller = new VendingMachineController();
    private int selectedProduct;
    private CashBundle change;
    private List<Product> selectedProducts;
    private VendingMachine vendingMachine;

    public VendingMachineInterface(Slots slots, List<Product> selectedProducts) {
        this.slots = slots;
        this.selectedProducts = selectedProducts;
        this.vendingMachine = new VendingMachine(selectedProducts);
    }

    /**
     * Displays the available products in the vending machine to the user.
     * The user can select individual products or product sets.
     */
    public void displayProducts() {
        Stage stage = new Stage();
        VBox vbox = new VBox();
        ListView<String> productListView = new ListView<>();
    
        for (Product product : selectedProducts) {
            String productDetails = String.format("%s - Calories: %d - Price: %d",
                    product.getName(), product.getCalories(), product.getPrice());
            productListView.getItems().add(productDetails);
        }
    
        for (ProductSet productSet : Product.productSets) { // <-- Corrected variable name here
            String productSetDetails = getProductSetDetails(productSet);
            productListView.getItems().add(productSetDetails);
        }
    
        Button selectButton = new Button("Select Product");
        selectButton.setOnAction(e -> {
            int selectedIndex = productListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) { // Check if an item is selected
                if (selectedIndex < selectedProducts.size()) {
                    Product selectedProduct = selectedProducts.get(selectedIndex);
                    selectProduct(selectedProduct.getSelectionNumber());
                } else {
                    int productSetIndex = -1;
                    int counter = selectedProducts.size();
                    for (ProductSet productSet : Product.productSets) {
                        productSetIndex++;
                        if (counter == selectedIndex) {
                            for (Map.Entry<Product, Integer> entry : productSet.getIncludedProducts().entrySet()) {
                                Product includedProduct = entry.getKey();
                                int quantity = entry.getValue();
                                System.out.println(includedProduct.getName() + " - Quantity: " + quantity);
                            }
                            selectProduct(productSet.getMainProduct().getSelectionNumber());
                            break;
                        }
                        counter++;
                    }
                    if (productSetIndex >= Product.productSets.size()) {
                        displayError("Invalid selection.");
                    }
                }
            } else {
                displayError("Please select a product or product set.");
            }
        });
    
        vbox.getChildren().addAll(productListView, selectButton);
        Scene scene = new Scene(vbox, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

     /**
     * Handles the selection of a product based on the product index.
     * If the product is sold individually, it prompts the user to enter cash.
     * If not, it displays an error message.
     *
     * @param productIndex The index of the selected product.
     */
    public void selectProduct(int productIndex) {
        Product product = getProductBySelectionNumber(productIndex);

        if (product != null && product.isSoldIndividually()) {
            this.selectedProduct = productIndex;
            displayEnterCashMessage();
        } else {
            displayError("Invalid product selection. The selected product is not sold individually. Please choose a different product.");
        }
    }
    
    /**
     * Displays a message to prompt the user to enter cash.
     */
    public void displayEnterCashMessage() {
        Stage stage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        Label enterCashLabel = new Label("Please enter cash as follows:");
        gridPane.add(enterCashLabel, 0, 0, 2, 1);

        Label exampleLabel = new Label("Example: If you would like to enter 2 ten: 0,2,0,0,0");
        gridPane.add(exampleLabel, 0, 1, 2, 1);

        TextField cashInputField = new TextField();
        gridPane.add(cashInputField, 0, 2, 2, 1);

        Button enterCashButton = new Button("Enter Cash");
        enterCashButton.setOnAction(e -> {
            try {
                String cashInput = cashInputField.getText();
                int[] cash = Cash.parseCash(cashInput);
                enterCash(cash);
            } catch (NumberFormatException ex) {
                displayError("Invalid cash input. Please enter a comma-separated list of integers.");
            }
        });
        gridPane.add(enterCashButton, 0, 3, 2, 1);

        Scene scene = new Scene(gridPane, 400, 400);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Processes the entered cash by creating a vending machine request and calculating the change.
     * 
     * @param cash The amounts of each denomination of cash entered.
     */
    public void enterCash(int... cash) {
        VendingMachineRequest request = new VendingMachineRequest(selectedProduct, cash);
        change = controller.calculateChange(request);
        displayChangeMessage(change);
    }

    /**
     * Displays the change information to the user.
     *
     * @param change The CashBundle containing the change amounts.
     */
    public void displayChangeMessage(CashBundle change) {
        Stage stage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        Label changeLabel = new Label("Your change is: " + change.getTotal() + " split as follows:");
        gridPane.add(changeLabel, 0, 0, 2, 1);

        Label hundredLabel = new Label("100: " + change.number100);
        gridPane.add(hundredLabel, 0, 1, 2, 1);

        Label fiftyLabel = new Label("50: " + change.number50);
        gridPane.add(fiftyLabel, 0, 2, 2, 1);

        Label twentyLabel = new Label("20: " + change.number20);
        gridPane.add(twentyLabel, 0, 3, 2, 1);

        Label tenLabel = new Label("10: " + change.number10);
        gridPane.add(tenLabel, 0, 4, 2, 1);

        Label fiveLabel = new Label("5: " + change.number5);
        gridPane.add(fiveLabel, 0, 5, 2, 1);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());
        gridPane.add(closeButton, 0, 6, 2, 1);

        Scene scene = new Scene(gridPane, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Displays an error message to the user.
     * The error message is shown in a separate window.
     *
     * @param message The error message to display.
     */
    public void displayError(String message) {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            Label errorLabel = new Label(message);

            GridPane gridPane = new GridPane();
            gridPane.add(errorLabel, 0, 0);

            Scene scene = new Scene(gridPane, 600, 100);
            stage.setScene(scene);
            stage.show();
        });
    }

    /**
     * Retrieves a product from the selectedProducts list based on its selection number.
     *
     * @param selectionNumber The selection number of the product to retrieve.
     * @return The Product object with the given selection number, or null if not found.
     */
    private Product getProductBySelectionNumber(int selectionNumber) {
        for (Product product : selectedProducts) {
            if (product.getSelectionNumber() == selectionNumber) {
                return product;
            }
        }
        return null; // Product not found
    }
    
    /**
     * Retrieves the details of a product set for displaying to the user.
     * The details include the main product's name, total calories, total price,
     * and the names and quantities of included products.
     *
     * @param productSet The product set to retrieve details for.
     * @return A string containing the details of the product set.
     */
    private String getProductSetDetails(ProductSet productSet) {
        StringBuilder details = new StringBuilder();
        details.append(productSet.getMainProduct().getName());
        details.append(" - Calories: ").append(productSet.getTotalCalories());
        details.append(" - Price: ").append(productSet.getTotalPrice());
        details.append(" - Includes: ");
        for (Map.Entry<Product, Integer> entry : productSet.getIncludedProducts().entrySet()) {
            Product includedProduct = entry.getKey();
            int quantity = entry.getValue();
            details.append(includedProduct.getName()).append(" (Quantity: ").append(quantity).append("), ");
        }
        // Remove the trailing comma and space
        details.delete(details.length() - 2, details.length());
        return details.toString();
    }

}