import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The ProductSet class represents a set of related products in a vending machine.
 * A product set consists of a main product and a collection of included products, each with a quantity.
 * The class provides methods to manage the products in the set and calculate the total price and calories.
 * @author Ceriola, Antonio
 */

public class ProductSet {
    private Product mainProduct;
    private Map<Product, Integer> includedProducts;

    /**
     * Constructs a new ProductSet with the specified main product details.
     * The set is initialized with an empty list of included products.
     *
     * @param selectionNumber The selection number of the main product.
     * @param stock           The initial stock of the main product.
     * @param price           The price of the main product.
     * @param calories        The calorie count of the main product.
     * @param soldIndiv       Indicates if the main product is sold individually or part of a set.
     * @param name            The name of the main product.
     */
    public ProductSet(int selectionNumber, int stock, int price, int calories, boolean soldIndiv, String name) {
        this.mainProduct = new Product(selectionNumber, stock, price, calories, soldIndiv, name);
        includedProducts = new HashMap<>();
    }

    /**
     * Adds an included product to the product set with the specified quantity.
     *
     * @param product  The included product to add.
     * @param quantity The quantity of the included product in the set.
     */
    public void addIncludedProduct(Product product, int quantity) {
        includedProducts.put(product, quantity);
    }

    /**
     * Retrieves the main product of the product set.
     *
     * @return The main product.
     */
    public Product getMainProduct() {
        return mainProduct;
    }

    /**
     * Retrieves the map of included products and their quantities in the set.
     *
     * @return A map of included products and their quantities.
     */
    public Map<Product, Integer> getIncludedProducts() {
        return includedProducts;
    }
    
     /**
     * Calculates the total price of the product set, including the main product and all included products.
     *
     * @return The total price of the product set.
     */
    public int getTotalPrice() {
        int totalPrice = mainProduct.getPrice();
        for (Map.Entry<Product, Integer> entry : includedProducts.entrySet()) {
            Product includedProduct = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += includedProduct.getPrice() * quantity;
        }
        return totalPrice;
    }
    
    /**
     * Calculates the total calories of the product set, including the main product and all included products.
     *
     * @return The total calories of the product set.
     */
    public int getTotalCalories() {
        int totalCalories = mainProduct.getCalories();
        for (Map.Entry<Product, Integer> entry : includedProducts.entrySet()) {
            Product includedProduct = entry.getKey();
            int quantity = entry.getValue();
            totalCalories += includedProduct.getCalories() * quantity;
        }
        return totalCalories;
    }
    
    /**
     * Reduces the stock of products in the set when it is purchased.
     * The stock of the main product is reduced by 1, and the stock of included products is reduced by their quantities.
     */
    public void reduceStock() {
        mainProduct.reduceStock(1);
        for (Map.Entry<Product, Integer> entry : includedProducts.entrySet()) {
            Product includedProduct = entry.getKey();
            int quantity = entry.getValue();
            includedProduct.reduceStock(quantity);
        }
    }

}
