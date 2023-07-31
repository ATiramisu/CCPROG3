/** The class Cash is that class used to initialize the Cash deniminations the customer can use
 * 
 * @author Ceriola, Antonio
 * 
 */

public enum Cash {
    FIVE(5), TEN(10),  TWENTY(20), FIFTY(50), HUNDRED(100);

    private int value;

    /**
     * 
     * @param value value is the value of the denomination
     */
    Cash(int value){
        this.value = value;
    }

    /**
     * 
     * @param cash cash the user inputs, dictated by its "index number"
     * @return returns the cash input in string form 
     */
    public static int[] parseCash(String cash){
        String[] numberCashInText = cash.split(",");
        int[] result = new int[numberCashInText.length];
        for(int index=0;index<numberCashInText.length;index++){
            result[index] = Integer.parseInt(numberCashInText[index]);
        }
        return result;
    }

    /**
     * 
     * @return returns the value input by customer
     */
    public int getValue(){
        return this.value;
    }
}