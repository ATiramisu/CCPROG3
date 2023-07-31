/** 
 * The class VendingMachineController is that class used to control the vending machine operations
 * 
 * @author Ceriola, Antonio
 */
public class VendingMachineController {
    private Calculator calculator;

    public VendingMachineController (){
    this.calculator = new Calculator();
    }
        /**
         * Calculates the change to be returned based on the entered cash and the selected product.
         * 
         * @param request The VendingMachineRequest object containing the entered cash and selected product
         * @return  The CashBundle object representing the calculated change.
         */
        public CashBundle calculateChange(VendingMachineRequest request) {
            int productPrice = request.getProduct().getPrice();
            int totalCashEntered = calculator.calculateTotal(request.getCash());
            int amountMoneyToReturn = totalCashEntered - productPrice;
            return calculator.calculateChange(amountMoneyToReturn);
        }

}


