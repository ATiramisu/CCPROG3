import java.util.ArrayList;
import java.util.List;

/**
 * The Slots class represents the storage slots in a vending machine.
 * Each slot can hold multiple products, and the class provides methods to manage
 * the products stored in the slots.
 * 
 * @author Ceriola, Antonio
 * 
 */
public class Slots {
     private List<List<Product>> products; 
    private int numSlots;           // Number of slots
    private int numItemsPerSlot;    // Number of items per slot

    /**
     * Constructs a new Slots instance with the specified number of slots and items per slot.
     * Each slot is initialized with empty products.
     *
     * @param numSlots        The number of slots in the vending machine.
     * @param numItemsPerSlot The number of items each slot can hold.
     */
    public Slots(int numSlots, int numItemsPerSlot) {
                products = new ArrayList<>();
        for (int i = 0; i < numSlots; i++) {
            List<Product> slotProducts = new ArrayList<>();
            for (int j = 0; j < numItemsPerSlot; j++) {
                slotProducts.add(Product.EMPTY); // Initialize with empty products
            }
            products.add(slotProducts);
    }
    }

     /**
     * Gets the product stored in the specified slot and item index.
     *
     * @param slotIndex The index of the slot.
     * @param itemIndex The index of the item within the slot.
     * @return The product stored in the specified slot and item index, or null if the indices are invalid.
     */
    public Product getProduct(int slotIndex, int itemIndex) {
        if (slotIndex >= 0 && slotIndex < products.size()) {
            List<Product> slotProducts = products.get(slotIndex);
            if (itemIndex >= 0 && itemIndex < slotProducts.size()) {
                return slotProducts.get(itemIndex);
            }
        }
        return null; // Return null for invalid indices
    }

     /**
     * Sets the product in the specified slot and item index.
     *
     * @param slotIndex The index of the slot.
     * @param itemIndex The index of the item within the slot.
     * @param product   The product to be set in the specified slot and item index.
     */
    public void setProduct(int slotIndex, int itemIndex, Product product) {
        if (slotIndex >= 0 && slotIndex < products.size()) {
            List<Product> slotProducts = products.get(slotIndex);
            if (itemIndex >= 0 && itemIndex < slotProducts.size()) {
                slotProducts.set(itemIndex, product);
            }
        }
    }

    /**
     * Retrieves all the products stored in the vending machine's slots as a single list.
     *
     * @return A list containing all the products stored in the slots.
     */
    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        for (List<Product> slotProducts : products) {
            allProducts.addAll(slotProducts);
        }
        return allProducts;
    }

    /**
     * Gets the product stored in the vending machine based on its selection number.
     *
     * @param selectionNumber The selection number of the product to retrieve.
     * @return The product with the specified selection number, or null if not found.
     */
    public Product getProductBySelectionNumber(int selectionNumber) {
    for (List<Product> slotProducts : products) {
        for (Product product : slotProducts) {
            if (product.getSelectionNumber() == selectionNumber) {
                return product;
            }
        }
    }
    return null; // Product not found
    }
    
    /**
     * Gets the total number of slots in the vending machine.
     *
     * @return The number of slots in the vending machine.
     */
    public int getNumSlots() {
        return numSlots;
    }

    /**
     * Gets the number of items each slot can hold.
     *
     * @return The number of items per slot.
     */
    public int getNumItemsPerSlot() {
        return numItemsPerSlot;
    }
}