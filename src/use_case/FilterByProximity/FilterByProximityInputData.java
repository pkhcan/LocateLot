package use_case.FilterByProximity;

/**
 * Input data for filter by proximity use case
 */

public class FilterByProximityInputData {

    private String address;

    /**
     * Constructor method
     * @param address from user input
     */
    public FilterByProximityInputData(String address) {
        this.address = address;
    }

    /**
     * getter method for input data address
     * @return
     */
    public String getAddress() {
        return this.address;
    }

}
