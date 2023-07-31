import java.util.ArrayList;
import java.util.List;

/**
 * The MaintenanceManager class represents the maintenance manager of a vending machine.
 * It is responsible for performing maintenance tasks such as restocking products, setting item prices,
 * replenishing cash, and collecting money from the vending machine.
 * 
 * @author Ceriola, Antonio
 */
public class MaintenanceManager {
    private Slots slots;
    private CashBundle cashBundle;
    private int numSlots;
    private int numItemsPerSlot;
    private List<Product> products;

        /**
     * Constructs a new MaintenanceManager with the specified number of slots and items per slot.
     *
     * @param numSlots The number of slots in the vending machine.
     * @param numItemsPerSlot The number of items per slot in the vending machine.
     */
    public MaintenanceManager(int numSlots, int numItemsPerSlot) {
        this.numSlots = numSlots;
        this.numItemsPerSlot = numItemsPerSlot;
        slots = new Slots(numSlots, numItemsPerSlot);
        cashBundle = new CashBundle(0, 0, 0, 0, 0);
        products = new ArrayList<>();
    }

    /**
     * Restocks all products in the vending machine.
     * Sets the stock of all products to a predefined value (e.g., 10) to restock them.
     * Displays an information dialog using the MaintenanceManagerInterface to inform the user that all products have been restocked.
     */
    public void restockProducts() {
        for (Product product : products) {
            product.setStock(10);
        }
        MaintenanceManagerInterface.showInfoDialog("All products have been restocked.");
    }

    /**
     * Sets the price for a specific product slot.
     * Searches for the product with the given selection number in the products list.
     * If found, sets the new price for the product and displays an information dialog using the MaintenanceManagerInterface.
     * If not found, displays an information dialog using the MaintenanceManagerInterface indicating that no product was found with the given selection number.
     *
     * @param selectionNumber The selection number of the product to set the price for.
     * @param price The new price to set for the product.
     */
    public void setItemPrice(int selectionNumber, int price) {
        Product product = getProductBySelectionNumber(selectionNumber);
        if (product != null) {
            product.setPrice(price);
            MaintenanceManagerInterface.showInfoDialog("Price updated for product with selection number " + selectionNumber);
        } else {
            MaintenanceManagerInterface.showInfoDialog("No product found with selection number " + selectionNumber);
        }
    }


    /**
     * Adds a new product to the vending machine.
     *
     * @param product The product to be added to the vending machine.
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Replenishes the cash in the vending machine if it is below a certain threshold (e.g., 25).
     * Checks the available cash and, if any denomination is below the threshold, replenishes it to the threshold value.
     * Displays an information dialog using the MaintenanceManagerInterface to inform the user about the replenishment.
     */
    public void replenishCash() {
        if (cashBundle.getNumber5() < 25) {
            cashBundle.setNumber5(25);
            MaintenanceManagerInterface.showInfoDialog("Cash of 5 has been replenished.");
        }
        if (cashBundle.getNumber10() < 25) {
            cashBundle.setNumber10(25);
            MaintenanceManagerInterface.showInfoDialog("Cash of 10 has been replenished.");
        }
        if (cashBundle.getNumber20() < 25) {
            cashBundle.setNumber20(25);
            MaintenanceManagerInterface.showInfoDialog("Cash of 20 has been replenished.");
        }
        if (cashBundle.getNumber50() < 25) {
            cashBundle.setNumber50(25);
            MaintenanceManagerInterface.showInfoDialog("Cash of 50 has been replenished.");
        }
        if (cashBundle.getNumber100() < 25) {
            cashBundle.setNumber100(25);
            MaintenanceManagerInterface.showInfoDialog("Cash of 100 has been replenished.");
        }
    }

    /**
     * Collects money from the vending machine for denominations exceeding a certain threshold (e.g., 25).
     * Checks the available cash and, if any denomination exceeds the threshold, displays the total cash in the machine
     * and the denominations with amounts above the threshold using the MaintenanceManagerInterface.
     */
    public void collectMoney() {
        String message = "Total cash in the machine: " + cashBundle.getTotal() + "\n";
        message += "Cash values over 25:\n";
        if (cashBundle.getNumber5() > 25) {
            message += "5: " + cashBundle.getNumber5() + "\n";
        }
        if (cashBundle.getNumber10() > 25) {
            message += "10: " + cashBundle.getNumber10() + "\n";
        }
        if (cashBundle.getNumber20() > 25) {
            message += "20: " + cashBundle.getNumber20() + "\n";
        }
        if (cashBundle.getNumber50() > 25) {
            message += "50: " + cashBundle.getNumber50() + "\n";
        }
        if (cashBundle.getNumber100() > 25) {
            message += "100: " + cashBundle.getNumber100() + "\n";
        }
        MaintenanceManagerInterface.showInfoDialog(message);
    }

    /**
     * Helper method to get a product from the products list based on its selection number.
     * Searches for the product with the given selection number in the products list.
     * If found, returns the product. Otherwise, returns null.
     *
     * @param selectionNumber The selection number of the product to find.
     * @return The product with the given selection number, or null if not found.
     */
    private Product getProductBySelectionNumber(int selectionNumber) {
        for (Product product : products) {
            if (product.getSelectionNumber() == selectionNumber) {
                return product;
            }
        }
        return null;
    }

    /**
     * Sets the Slots instance representing the vending machine slots.
     *
     * @param slots The Slots instance representing the vending machine slots.
     */
    public void setSlots(Slots slots) {
        this.slots = slots;
    }

    /**
     * Gets the Slots instance representing the vending machine slots.
     *
     * @return The Slots instance representing the vending machine slots.
     */
    public Slots getSlots() {
        return slots;
    }
}