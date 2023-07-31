/** 
 * The CashBundle class represents a bundle of cash with different denominations. 
 * It keeps track of the number of bills for each denomination (5, 10, 20, 50, 100).
 * 
 * @author Ceriola, Antonio
 */


public class CashBundle {
    public int number5 = 25;
    public int number10 = 25;
    public int number20 = 25;
    public int number50 = 25;
    public int number100 = 25;

    /**
     * Creates a CashBundle object with the specified number of bills per denomination
     * 
     * @param enteredCash An array representing the number of bills for each denomination.
     */
    public CashBundle(int... enteredCash) {
        this.number5 = enteredCash[0];
        this.number10 = enteredCash[1];
        this.number20 = enteredCash[2];
        this.number50 = enteredCash[3];
        this.number100 = enteredCash[4];
    }

    /**
     * Calculates the total value of the cash bundle.
     * 
     * @return The total value of the cash bundle.
     */
    public int getTotal(){
        int total = 0;
        total = total+this.number5*Cash.FIVE.getValue();
        total = total+this.number10*Cash.TEN.getValue();
        total = total+this.number20*Cash.TWENTY.getValue();
        total = total+this.number50*Cash.FIFTY.getValue();
        total = total+this.number100*Cash.HUNDRED.getValue();
        return total;
    }

    /**
     *  Retrieves the number of bills for 5 denomination.
     * 
     * @return The number of 5 bills in the cash bundle.
     */
    public int getNumber5(){
        return number5;
    }

    /**
     *  Retrieves the number of bills for 10 denomination.
     * 
     * @return The number of 10 bills in the cash bundle.
     */
    public int getNumber10(){
        return number10;
    }

    /**
     *  Retrieves the number of bills for 20 denomination.
     * 
     * @return The number of 20 bills in the cash bundle.
     */
    public int getNumber20(){
        return number20;
    }

    /**
     *  Retrieves the number of bills for 50 denomination.
     * 
     * @return The number of 50 bills in the cash bundle.
     */
    public int getNumber50(){
        return number50;
    }

    /**
     *  Retrieves the number of bills for 100 denomination.
     * 
     * @return The number of 100 bills in the cash bundle.
     */
    public int getNumber100(){
        return number100;
    }

    /**
     * Sets the number of bills for 5 denomination
     * 
     * @param number5 The number of 5 bills to set.
     */
    public void setNumber5(int number5){
        this.number5 = number5;
    }

    /**
     * Sets the number of bills for 10 denomination
     * 
     * @param number10 The number of 10 bills to set.
     */
    public void setNumber10(int number10){
        this.number10 = number10;
    }

    /**
     * Sets the number of bills for 20 denomination
     * 
     * @param number20 The number of 20 bills to set.
     */
    public void setNumber20(int number20){
        this.number20 = number20;
    }

    /**
     * Sets the number of bills for 50 denomination
     * 
     * @param number50 The number of 50 bills to set.
     */
    public void setNumber50(int number50){
        this.number50 = number50;
    }

    /**
     * Sets the number of bills for 100 denomination
     * 
     * @param number100 The number of 100 bills to set.
     */
    public void setNumber100(int number100){
        this.number100 = number100;
    }

}