import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a CreateVendingMachine that allows users to configure and create a vending machine.
 * Users can select products and set up slots for the vending machine.
 * 
 * @author Ceriola, Antonio
 */
public class CreateVendingMachine {

    private Slots slots;
    private int numSlots;
    private int numItemsPerSlot;
    private VBox root;
    private List<Product> selectedProducts = new ArrayList<>();
    private List<VBox> slotBoxes = new ArrayList<>();
    private VendingMachine vendingMachine;

    /**
     * Constructs a new CreateVendingMachine with the specified number of slots and items per slot.
     *
     * @param numSlots       The number of slots in the vending machine.
     * @param numItemsPerSlot The number of items that can be placed in each slot.
     */
    public CreateVendingMachine(int numSlots, int numItemsPerSlot) {
        this.numSlots = numSlots;
        this.numItemsPerSlot = numItemsPerSlot;
        this.slots = new Slots(numSlots, numItemsPerSlot);
    }

     /**
     * Sets the root VBox for the user interface of the vending machine.
     *
     * @param root The root VBox of the user interface.
     */
    public void setRoot(VBox root) {
        this.root = root;
    }

     /**
     * Creates and returns a VBox containing the graphical user interface for configuring item slots in the vending machine.
     * Users can select items to be placed in each slot using checkboxes.
     *
     * @param primaryStage   The primary stage of the JavaFX application.
     * @param root           The root VBox of the user interface.
     * @param numSlots       The number of slots in the vending machine.
     * @param numItemsPerSlot The number of items that can be placed in each slot.
     * @return A VBox containing the item slots graphical user interface.
     */
    public VBox createItemSlotsGUI(Stage primaryStage, VBox root, int numSlots, int numItemsPerSlot) {
    VBox container = new VBox();
    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setPadding(new Insets(10));
    createItemSlots();

    for (int i = 0; i < numSlots; i++) {
        VBox slotBox = new VBox();
        slotBox.setId("slot-" + (i + 1)); // Ensure correct id assignment (e.g., "slot-1", "slot-2", etc.)
        slotBox.getChildren().add(new Label("Select items for slot " + (i + 1) + ":"));
        slotBoxes.add(slotBox);
    }
    
        int numColumns = 5;
        gridPane.getColumnConstraints().clear();
        for (int i = 0; i < numColumns; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100.0 / numColumns);
            gridPane.getColumnConstraints().add(column);
        }
    
        int rowIndex = 1;
    for (Product preSetProduct : Product.getCustomProducts()) {
            VBox itemBox = new VBox();

            CheckBox selectedCheckBox = new CheckBox("Select");
            Label nameLabel = new Label("Name: " + preSetProduct.getName());
            Label priceLabel = new Label("Price: " + preSetProduct.getPrice());
            Label caloriesLabel = new Label("Calories: " + preSetProduct.getCalories());
            Label soldIndividuallyLabel = new Label("Can be sold individually: " + preSetProduct.isSoldIndividually());

            itemBox.getChildren().addAll(selectedCheckBox, nameLabel, priceLabel, caloriesLabel, soldIndividuallyLabel);

            // Set the event handler for the select button
            selectedCheckBox.setOnAction(e -> handleProductSelection(selectedCheckBox));

            int columnIndex = rowIndex % numColumns;
            int slotIndex = (rowIndex - 1) / numColumns;
            gridPane.add(itemBox, columnIndex, slotIndex);
    
            // Set the id for each slot VBox
            itemBox.setId("slot-" + (slotIndex + 1));
    
            rowIndex++;
        }
    
        // Add slot VBox elements to the container
        for (int i = 0; i < numSlots; i++) {
            container.getChildren().add(slotBoxes.get(i));
        }
    
        container.getChildren().addAll(gridPane);
        container.getChildren().add(createVendingMachineButton(primaryStage));
        return container;
    }

    /**
     * Event handler for product selection checkboxes. Adds or removes selected products from the vending machine.
     *
     * @param selectedCheckBox The checkbox representing the product selection.
     */
    private void handleProductSelection(CheckBox selectedCheckBox) {
        VBox itemBox = (VBox) selectedCheckBox.getParent();
        int slotIndex = Integer.parseInt(itemBox.getId().replace("slot-", "")) - 1; // Extract slot index from VBox ID
    
        if (selectedCheckBox.isSelected()) {
            // Set a background color for selected CheckBox
            selectedCheckBox.setStyle("-fx-background-color: green;");
    
            // Get the product details from the VBox
            Label nameLabel = (Label) itemBox.getChildren().get(1); // Assuming nameLabel is at index 1
            Label priceLabel = (Label) itemBox.getChildren().get(2); // Assuming priceLabel is at index 2
            Label caloriesLabel = (Label) itemBox.getChildren().get(3); // Assuming caloriesLabel is at index 3
            Label soldIndividuallyLabel = (Label) itemBox.getChildren().get(4); // Assuming soldIndividuallyLabel is at index 4
    
            // Extract the product details from the labels
            String name = nameLabel.getText().replace("Name: ", "");
            int price = Integer.parseInt(priceLabel.getText().replace("Price: ", ""));
            int calories = Integer.parseInt(caloriesLabel.getText().replace("Calories: ", ""));
            boolean soldIndividually = Boolean.parseBoolean(soldIndividuallyLabel.getText().replace("Can be sold individually: ", ""));
    
            // Create the Product object and add it to the selectedProducts list
            selectedProducts.add(new Product(slotIndex + 1, numItemsPerSlot, price, calories, soldIndividually, name));
            } else {
            // Reset background color for unselected CheckBox
            selectedCheckBox.setStyle("");
    
            // Remove the product from the selectedProducts list if it was unselected
            selectedProducts.removeIf(product -> product.getSelectionNumber() == slotIndex + 1);
        }
    }

    /**
     * Creates a new instance of the VendingMachine class and populates it with the selected products.
     * Then, displays the vending machine's graphical user interface to the user.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     */
    private void createVendingMachineInstance(Stage primaryStage) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < numSlots; i++) {
            VBox slotBox = slotBoxes.get(i);
            if (slotBox == null) {
                return;
            }
            slotBoxes.add(slotBox);
        }
    
        for (int i = 0; i < numSlots; i++) {
            VBox slotBox = slotBoxes.get(i);
        
            for (int j = 0; j < numItemsPerSlot; j++) {
                int checkBoxIndex = j * 5 + 1;
                if (checkBoxIndex < slotBox.getChildren().size()) {
                    Node node = slotBox.getChildren().get(checkBoxIndex);
                    if (node instanceof CheckBox) {
                        CheckBox selectedCheckBox = (CheckBox) node;
                        if (selectedCheckBox.isSelected()) {
                            Product selectedProduct = selectedProducts.get(i * numItemsPerSlot + j);
                        // Add the selected product's information to the slotBox
                        slotBox.getChildren().addAll(
                            new Label("Name: " + selectedProduct.getName()),
                            new Label("Price: " + selectedProduct.getPrice()),
                            new Label("Calories: " + selectedProduct.getCalories()),
                            new Label("Can be sold individually: " + selectedProduct.isSoldIndividually())
                        );
                        }
                    } 
                } 
            }
        }
    
        
        vendingMachine = new VendingMachine(selectedProducts);

        for (Product product : this.selectedProducts) {
            slots.setProduct(product.getSelectionNumber() - 1, product.getStock() - 1, product);
        }

        for (Product product : this.selectedProducts) {
            vendingMachine.addProduct(product);
        }
        
        VBox newRoot = new VBox();
        newRoot.getChildren().addAll(root.getChildren());
       
        VendingMachineInterface vendingMachineInterface = new VendingMachineInterface(getSlots(), selectedProducts);


        Scene scene = new Scene(newRoot, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vending Machine Factory Simulator");
        primaryStage.show();
    }

     /**
     * Creates and returns a Button that triggers the process of creating the vending machine when clicked.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     * @return A Button for creating the vending machine.
     */
    private Button createVendingMachineButton(Stage primaryStage) {
        Button createMachineButton = new Button("Create Vending Machine");
        createMachineButton.setOnAction(e -> createVendingMachineInstance(primaryStage));
        return createMachineButton;
    }

     /**
     * Creates empty item slots in the vending machine.
     */
    public void createItemSlots() {
        for (int i = 0; i < numSlots; i++) {
            for (int j = 0; j < numItemsPerSlot; j++) {
                slots.setProduct(i, j, Product.EMPTY);
            }
        }
    }

    /**
     * Returns the number of slots in the vending machine.
     *
     * @return The number of slots.
     */
    public int getNumSlots() {
        return numSlots;
    }

    /**
     * Returns the number of items that can be placed in each slot of the vending machine.
     *
     * @return The number of items per slot.
     */
    public int getNumItemsPerSlot() {
        return numItemsPerSlot;
    }

    /**
     * Returns the Slots object representing the vending machine's slots.
     *
     * @return The Slots object representing the vending machine's slots.
     */
    public Slots getSlots() {
        return slots;
    }
    
    /**
     * Creates and configures item slots in the vending machine with the provided names, prices, calories, and soldIndividually data.
     *
     * @param names            An array of names for each item slot.
     * @param prices           An array of prices for each item slot.
     * @param calories         An array of calories for each item slot.
     * @param soldIndividually An array of boolean values indicating whether each item can be sold individually.
     */
    public void createItemSlots(String[] names, int[] prices, int[] calories, boolean[] soldIndividually) {
        for (int i = 0; i < numSlots; i++) {
            for (int j = 0; j < numItemsPerSlot; j++) {
                String name = names[i * numItemsPerSlot + j];
                int price = prices[i * numItemsPerSlot + j];
                int cal = calories[i * numItemsPerSlot + j];
                boolean sold = soldIndividually[i * numItemsPerSlot + j];
    
                // Create the product with the user-provided details
                Product product = new Product(i + 1, j + 1, price, cal, sold, name);
    
                // Assign the product to the appropriate slot and item
                slots.setProduct(i, j, product);
            }
        }
    }

     /**
     * Returns the list of selected products that will be placed in the vending machine's slots.
     *
     * @return The list of selected products.
     */
    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }
    
}
