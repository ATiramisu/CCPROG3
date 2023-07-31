/** 
 * The class VendingMachineRequest is that class represents the request the user made when operating the vending machine.
 * 
 * @author Ceriola, Antonio
 */


public class VendingMachineRequest {
    public Product product;
    public CashBundle enteredCash;

    /**
     * creates a new VendingMachineRequest object the the details seleted by the user
     * 
     * @param selectedProduct The selection number of the desired product.
     * @param enteredCash The cash entered by the user as an array of denominations.
     */
    public VendingMachineRequest(int selectedProduct, int... enteredCash) {
        this.product = Product.valueOf(selectedProduct);
        this.enteredCash = new CashBundle(enteredCash);
    }
    
    /**
     * Get the selected product from the vending machine request.
     * 
     * @return The selected product.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Get the cash entered by the user from the vending machine request.
     * 
     * @return The cash entered as a CashBundle object.
     */
    public CashBundle getCash() {
        return enteredCash;
    }
    
}