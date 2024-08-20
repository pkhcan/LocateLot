package use_case.FilterByPrice;


/**
 * Represents input data related to filter by price with an address and the user's local time.
 */
public class FilterByPriceInputData {

    final String address;
    final int time;

    /**
     * Constructs an {@code FilterByPriceInputData} object with the specified address and collected time.
     *
     * @param address the address associated with the input data
     * @param time the input time of the user
     */
    public FilterByPriceInputData(String address, int time) {
        this.address = address;
        this.time = time;
    }

    /**
     * Retrieves the time associated with this input data.
     *
     * @return the int value of the time
     */
    public int getTime(){return this.time;}

    /**
     * Retrieves the address associated with this input data.
     *
     * @return the String value of the address
     */
    public String getAddress(){return this.address;}
}

