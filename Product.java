/**
 * The Product class is the class used to store the information of the products
 * 
 * @author Ceriola, Antonio
 */

import java.util.ArrayList;
import java.util.List;

public class  Product {
    public static final Product EMPTY = new Product(-1, 0, 0, 0, false, "");
    public static final Product CUSTOM = new Product(0, 0, 0, 0, false, "");
    public static List<Product> allProducts = new ArrayList<>(); 
    public static List<ProductSet> productSets = new ArrayList<>();

    private int selectionNumber;
    private int price;
    private int calories;
    private boolean soldIndiv;
    private int stock;
    private String name;
    private boolean partOfSet;

    /**
     * creates the preset products that the user can select
     */
    static {
        allProducts.add(new Product(1, 10, 100, 250, true, "Vanilla Ice Cream"));
        allProducts.add(new Product(2, 10, 100, 250, true, "Chocolate Ice Cream"));
        allProducts.add(new Product(3, 10, 100, 250, true, "Coffee Ice Cream"));
        allProducts.add(new Product(4, 10, 100, 250, true, "Toffee Ice Cream"));
        allProducts.add(new Product(5, 10, 100, 250, true, "Caramel Ice Cream"));
        allProducts.add(new Product(6, 10, 100, 250, true, "Honey Ice Cream"));
        allProducts.add(new Product(7, 10, 100, 250, true, "Cookies and Cream Ice Cream"));
        allProducts.add(new Product(8, 10, 100, 250, true, "Banana Ice Cream"));
        allProducts.add(new Product(9, 10, 100, 250, true, "Strawberry Ice Cream"));
        allProducts.add(new Product(10, 10, 100, 250, true, "Melon Ice Cream"));
        allProducts.add(new Product(11, 10, 100, 250, true, "Coconut Ice Cream"));
        allProducts.add(new Product(12, 10, 100, 250, true, "Raspberry Ice Cream"));
        allProducts.add(new Product(13, 10, 100, 250, true, "Blueberry Ice Cream"));
        allProducts.add(new Product(14, 10, 100, 250, true, "Orange Ice Cream"));
        allProducts.add(new Product(15, 10, 100, 250, true, "Mango Ice Cream"));
        allProducts.add(new Product(16, 10, 50, 100, false, "Sliced Banana"));
        allProducts.add(new Product(17, 10, 20, 45, false, "Corn Flakes"));
        allProducts.add(new Product(18, 10, 15, 25, false, "Sprinkles"));
        allProducts.add(new Product(19, 10, 50, 150, true, "Cookie"));
        allProducts.add(new Product(20, 10, 25, 25, false, "Milk"));
    
         // Creating the product sets
        ProductSet bananaSplit = new ProductSet(1, 10, 165, 250, true, "Banana Split");
            bananaSplit.addIncludedProduct(new Product(16, 10, 50, 100, false, "Sliced Banana"), 1);
            bananaSplit.addIncludedProduct(new Product(18, 10, 15, 25, false, "Sprinkles"), 1);

        ProductSet iceCreamSandwich = new ProductSet(2, 10, 200, 250, true, "Ice Cream Sandwich");
            iceCreamSandwich.addIncludedProduct(new Product(19, 10, 50, 150, true, "Cookie"), 2);

        ProductSet milkshake = new ProductSet(3, 10, 175, 250, true, "Milkshake");
            milkshake.addIncludedProduct(new Product(16, 10, 50, 100, false, "Sliced Banana"), 1);
            milkshake.addIncludedProduct(new Product(20, 10, 25, 25, false, "Milk"), 1);

        // Add the product sets to the allProducts list
        productSets.add(bananaSplit);
        productSets.add(iceCreamSandwich);
        productSets.add(milkshake);

    }

    public static List<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * Creates a new product with the given properties (for custom products)
     * 
     */
    Product() {
        this.selectionNumber = 0;
        this.price = 0;
        this.calories = 0;
        this.soldIndiv = false;
        this.stock = 0;
        this.name = "";
    }

    /**
     * Creates a new product with the given properties
     * 
     * @param selectionNumber the selection number of the product
     * @param price the price of the product
     * @param calories the calories the product contains
     * @param soldIndiv indicatd if the product can be bought individually
     * @param name Name of the product
     */
    public Product(int selectionNumber, int stock, int price, int calories, boolean soldIndiv, String name) {
        this.selectionNumber = selectionNumber;
        this.price = price;
        this.calories = calories;
        this.soldIndiv = soldIndiv;
        this.stock = stock;
        this.name = name;
        this.partOfSet = false;
    }

    /**
     * Returns an array of all custom products. Excludes EMPTY and CUSTOM itself.
     *
     * @return an array of all custom products.
     */
    public static Product[] getCustomProducts() {
        List<Product> customProductsList = new ArrayList<>();
        for (Product product : allProducts) {
            if (product != EMPTY && product != CUSTOM) {
                customProductsList.add(product);
            }
        }
        return customProductsList.toArray(new Product[0]);
    }

    /**
     * 
     * @return the selection number of the product
     */
    public int getSelectionNumber(){
        return selectionNumber;
    }

    /**
     * 
     * @return the price of the product
     */
    public int getPrice() {
        return price;
    }

    /**
     * 
     * @return the calories of the product
     */
    public int getCalories() {
        return calories;
    }

    /**
     * 
     * @return if the product is sold individually or not
     */
    public boolean isSoldIndividually() {
        return soldIndiv;
    }

   /**
     * 
     * @return if the product is part of a set or not
     */
    public boolean isPartOfSet() {
        return partOfSet;
    }

    /**
     * 
     * @return the stock quantity.
     */
    public int getStock() {
        return stock;
    }
    
    /**
     * 
     * @return the name of product.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the selection number of the product.
     * 
     * @param selectionNumber the new selection number.
     */
    public void setSelectionNumber(int selectionNumber){
        this.selectionNumber = selectionNumber;
    }

    /**
     * Sets the price of the product.
     * 
     * @param price the new price of the product.
     */
    public void setPrice(int price){
        this.price = price;
    }

    /**
     * Sets the calories of the product.
     * 
     * @param calories the new amount of calories in the product.
     */
    public void setCalories(int calories){
        this.calories = calories;
    }

    /**
     * Sets if the product can be sold individually of the product.
     * 
     * @param soldIndiv true if the product is sold individually, false otherwise.
     */
    public void setSoldIndividually(boolean soldIndiv){
        this.soldIndiv = soldIndiv;
    }

    /**
     * Sets if the product can be sold individually of the product.
     * 
     * @param partOfSet true if the product is sold in a set, false otherwise.
     */
    public void setPartOfSet(boolean partOfSet) {
        this.partOfSet = partOfSet;
    }

    /**
     * Sets the stock number of the product.
     * 
     * @param stock The new stock quantity.
     */
    public void setStock(int stock){
        this.stock = stock;
    }

    /**
     * Sets the name of the product.
     * 
     * @param name the new name of the product.
     */
    public void setName(String name){
        this.name = name;
    }

     /**
     * Reduces the stock quantity of the product by 1.
     * 
     */
    public void reduceStock() {
        if (stock > 0) {
            stock--;
        }
    }

    /**
     * Returns the Product constant associated with the given selection number. 
     * If no matching product is found, returns the EMPTY constant.
     * @param selectionNumber The selection number of the product to find.
     * @return The Product constant matching the selection number, or EMPTY if not found.
     */
    public static Product valueOf(int selectionNumber) {
        for (Product product : allProducts) {
            if (selectionNumber == product.getSelectionNumber()) {
                return product;
            }
        }
        return EMPTY;
    }

        /**
     * Updates the details of the product
     * 
     * @param name Name of the product
     * @param price the price of the product
     * @param calories the calories the product contains
     * @param soldIndiv indicatd if the product can be bought individually
     */
    public void updateDetails(String name, int price, int calories, boolean soldIndiv) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.soldIndiv = soldIndiv;
    }

    /**
     * Checks if product slot is empty 
     * 
     */
    public boolean isEmpty() {
        return this == EMPTY;
    }

    public void reduceStock(int quantity) {
        if (stock >= quantity) {
            stock -= quantity;
        }
    }

}
