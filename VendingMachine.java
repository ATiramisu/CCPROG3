import java.util.List;
import java.util.ArrayList;

/**
 * The VendingMachine class represents a vending machine that stores and manages products.
 * It allows adding, removing, and updating products in the vending machine's product database.
 * The vending machine can also display all the products it contains.
 * 
 * This class can be used to create and manage a virtual vending machine.
 * 
 * @author Ceeriola, Antonio
 */
public class VendingMachine {
    private List<Product> selectedProducts;
    private List<Product> products;

    /**
     * Constructs an empty vending machine with an empty product list.
     */
    public VendingMachine() {
        this.products = new ArrayList<>();
    }

    /**
     * Constructs a vending machine with the specified list of selected products.
     * 
     * @param selectedProducts The list of selected products to initialize the vending machine.
     */
    public VendingMachine(List<Product> selectedProducts) {
        this();
        this.selectedProducts = selectedProducts;
    }
    
    /**
     * Adds a product to the vending machine's product database.
     * 
     * @param product The product to be added.
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Removes a product from the vending machine's product database.
     * 
     * @param product The product to be removed.
     */
    public void removeProduct(Product product) {
        products.remove(product);
    }

    /**
     * Updates a product in the vending machine's product database.
     * The product is identified based on its selection number.
     * 
     * @param updatedProduct The updated product with the new information.
     */
    public void updateProduct(Product updatedProduct) {
        int index = findProductIndex(updatedProduct.getSelectionNumber());
        if (index != -1) {
            products.set(index, updatedProduct);
        } else {
            // Product not found, handle error or add it as a new product
            // For example, you can display an error message:
            System.out.println("Error: Product with selection number " + updatedProduct.getSelectionNumber() + " not found.");
        }
    }

    /**
     * Find the desired product using the slection Number.
     * 
     * @param selectionNumber The selection Number of the product selected.
     */
    private int findProductIndex(int selectionNumber) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getSelectionNumber() == selectionNumber) {
                return i;
            }
        }
        return -1; // Product not found
    }

    /**
     * Displays all the products in the vending machine's product database.
     */
    public void displayProducts() {
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

     /**
     * Gets the list of products in the vending machine's product database.
     * 
     * @return The list of products.
     */
    public List<Product> getProducts() {
        return products;
    }

}
