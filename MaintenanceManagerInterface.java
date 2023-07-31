import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * The MaintenanceManagerInterface class represents the user interface for the maintenance manager of a vending machine.
 * It provides functionality for restocking products, setting item prices, replenishing cash, and collecting money.
 * 
 * @author Ceriola, Antonio
 */
public class MaintenanceManagerInterface {
    private MaintenanceManager maintenanceManager;

    /**
     * Constructs a new MaintenanceManagerInterface with the specified MaintenanceManager.
     *
     * @param maintenanceManager The MaintenanceManager instance to interact with.
     */
    public MaintenanceManagerInterface(MaintenanceManager maintenanceManager) {
        this.maintenanceManager = maintenanceManager;
    }

    /**
     * Starts the maintenance menu user interface.
     * Creates and displays the maintenance menu using JavaFX components in a separate window.
     *
     * @param primaryStage The primary stage to display the maintenance menu.
     */
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Button restockAllButton = new Button("Restock All Products");
        restockAllButton.setOnAction(e -> restockAllProducts());
        gridPane.add(restockAllButton, 0, 0);

        Button setItemPriceButton = new Button("Set Item Price");
        setItemPriceButton.setOnAction(e -> setItemPrice());
        gridPane.add(setItemPriceButton, 0, 1);

        Button replenishCashButton = new Button("Replenish Cash");
        replenishCashButton.setOnAction(e -> replenishCash());
        gridPane.add(replenishCashButton, 0, 2);

        Button collectMoneyButton = new Button("Collect Money");
        collectMoneyButton.setOnAction(e -> collectMoney());
        gridPane.add(collectMoneyButton, 0, 3);

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setTitle("Maintenance Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Restocks all products in the vending machine.
     * Calls the restockProducts() method of the MaintenanceManager to perform the restocking task.
     * Displays an information dialog to inform the user that all products have been restocked.
     */
    private void restockAllProducts() {
        maintenanceManager.restockProducts();
        showInfoDialog("All products have been restocked.");
    }

    /**
     * Sets the price for a specific product slot.
     * Prompts the user to enter the slot index and the new price using a TextInputDialog.
     * Calls the setItemPrice() method of the MaintenanceManager to set the price for the specified slot.
     * Displays an information dialog to inform the user that the price has been set for the slot.
     */
    private void setItemPrice() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Set Item Price");
        dialog.setHeaderText("Enter the slot index and the new price.");
        dialog.setContentText("Slot Index:");
        dialog.showAndWait().ifPresent(slotIndexInput -> {
            int slotIndex = Integer.parseInt(slotIndexInput);
            int price = showPriceInputDialog("Enter the new price for slot " + slotIndex + ":");
            maintenanceManager.setItemPrice(slotIndex, price);
            showInfoDialog("Price has been set for slot " + slotIndex + ".");
        });
    }

    /**
     * Helper method to show a dialog for price input.
     * Prompts the user to enter the new price using a TextInputDialog.
     * Parses the user input and returns the entered price as an integer.
     *
     * @param message The message to display in the price input dialog.
     * @return The entered price as an integer.
     */
    private int showPriceInputDialog(String message) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(message);
        dialog.showAndWait();
        String input = dialog.getResult();
        return Integer.parseInt(input);
    }

    /**
     * Replenishes the cash in the vending machine.
     * Calls the replenishCash() method of the MaintenanceManager to perform the cash replenishment task.
     * Displays an information dialog to inform the user that the cash has been replenished.
     */
    private void replenishCash() {
        maintenanceManager.replenishCash();
        showInfoDialog("Cash has been replenished.");
    }

     /**
     * Collects all money from the vending machine.
     * Calls the collectMoney() method of the MaintenanceManager to perform the money collection task.
     * Displays an information dialog to inform the user that the money has been collected.
     */
    private void collectMoney() {
        maintenanceManager.collectMoney();
        showInfoDialog("Money has been collected.");
    }

    /**
     * Helper method to show an information dialog.
     * Displays an information dialog with the specified message.
     *
     * @param message The message to display in the information dialog.
     */
    public static void showInfoDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}