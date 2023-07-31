import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private CreateVendingMachine createVendingMachine = null;
    private MaintenanceManager maintenanceManager = null;
    private MaintenanceManagerInterface maintenanceManagerInterface = null;
    private VBox root;
    private List<Product> selectedProducts = new ArrayList<>();

    private Button createMachineButton;
    private Button testMachineButton;
    private Button manageMachineButton;
    private Button exitButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        root = new VBox();

        createMachineButton = new Button("Create a Vending Machine");
        createMachineButton.setOnAction(e -> testCreateVendingMachine(primaryStage));
        root.getChildren().add(createMachineButton);

        testMachineButton = new Button("Test a Vending Machine");
        testMachineButton.setOnAction(e -> testVendingMachine());
        root.getChildren().add(testMachineButton);

        manageMachineButton = new Button("Manage a Vending Machine");
        manageMachineButton.setOnAction(e -> manageVendingMachine());
        root.getChildren().add(manageMachineButton);

        exitButton = new Button("Exit");
        exitButton.setOnAction(e -> primaryStage.close());
        root.getChildren().add(exitButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Vending Machine Factory Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*-------------------------------------------------- */
    /* Create Vending Machine GUI */
    private void testCreateVendingMachine(Stage primaryStage) {
            Stage createVendingMachineStage = new Stage();
            createVendingMachineStage.setTitle("Create Vending Machine");
    
            Label labelNumSlots = new Label("Enter the number of slots in the vending machine: ");
            TextField textFieldNumSlots = new TextField();
            Label labelNumItemsPerSlot = new Label("Enter the number of items per slot: ");
            TextField textFieldNumItemsPerSlot = new TextField();
    
            Button createButton = new Button("Create Vending Machine");
            createButton.setOnAction(event -> {
                int numSlots = Integer.parseInt(textFieldNumSlots.getText());
                int numItemsPerSlot = Integer.parseInt(textFieldNumItemsPerSlot.getText());
                createVendingMachineStage.close(); // Close the popup window after creating the vending machine
    
                createVendingMachine = new CreateVendingMachine(numSlots, numItemsPerSlot);
                VBox itemSelectionGUI = createVendingMachine.createItemSlotsGUI(primaryStage, root, numSlots, numItemsPerSlot);

                if (itemSelectionGUI != null) {
                    Scene itemSelectionScene = new Scene(itemSelectionGUI, 800, 600);
                    primaryStage.setScene(itemSelectionScene);
                    primaryStage.setTitle("Create Vending Machine");
                    primaryStage.show();

                    createVendingMachine.setRoot(root);
                    selectedProducts = createVendingMachine.getSelectedProducts();
                }
            });
    
            VBox container = new VBox(labelNumSlots, textFieldNumSlots, labelNumItemsPerSlot, textFieldNumItemsPerSlot, createButton);
            container.setSpacing(10);
            container.setPadding(new Insets(10));
    
            Scene scene = new Scene(container, 400, 200);
            createVendingMachineStage.setScene(scene);
            createVendingMachineStage.show();
        }


    /*-------------------------------------------------- */
    /*Test Vending Machine GUI */

    private void testVendingMachine() {
        List<Product> selectedProducts = createVendingMachine.getSelectedProducts();
        if (createVendingMachine != null) {
            VendingMachineInterface machineInterface = new VendingMachineInterface(createVendingMachine.getSlots(), selectedProducts);
            machineInterface.displayProducts();

        } else {
            showAlert("Please create a vending machine first.");
        }
    }

    /*-------------------------------------------------- */
    /*Manage Vending Machine GUI */
    private void manageVendingMachine() {
        if (createVendingMachine != null) {
            maintenanceManager = new MaintenanceManager(createVendingMachine.getNumSlots(),
                    createVendingMachine.getNumItemsPerSlot());
            maintenanceManagerInterface = new MaintenanceManagerInterface(maintenanceManager);
    
            // Show the Maintenance Manager interface
            Stage maintenanceManagerStage = new Stage();
            maintenanceManagerInterface.start(maintenanceManagerStage);
        } else {
            showAlert("Please create a vending machine first.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
} 