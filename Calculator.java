/** The class Calculator is that class used to compute the change
 * 
 * @author Ceriola, Antonio
 * 
 */

public class Calculator {

    /**
     * 
     * @param enteredCash the cash enetered by the customer
     * @return the total change
     */
    public int calculateTotal(CashBundle enteredCash) {
        return enteredCash.getTotal();
    }

    /**
     * 
     * @param amountMoneyToReturn Change of the customer
     * @return the change the customer will recieve
     */

    public CashBundle calculateChange(int amountMoneyToReturn) {
        CashBundle change = new CashBundle(new int[5]);
        int remainingAmount = amountMoneyToReturn;
        change.number100 = remainingAmount / Cash.HUNDRED.getValue();
        remainingAmount = remainingAmount % Cash.HUNDRED.getValue();

        change.number50 = remainingAmount / Cash.FIFTY.getValue();
        remainingAmount = remainingAmount % Cash.FIFTY.getValue();

        change.number20 = remainingAmount / Cash.TWENTY.getValue();
        remainingAmount = remainingAmount % Cash.TWENTY.getValue();

        change.number10 = remainingAmount / Cash.TEN.getValue();
        remainingAmount = remainingAmount % Cash.TEN.getValue();

        change.number5 = remainingAmount / Cash.FIVE.getValue();


        return change;
    }
}