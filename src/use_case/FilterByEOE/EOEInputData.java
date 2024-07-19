package use_case.FilterByEOE;


/**
 * Represents input data related to EOE (Ease of Entry Reviews) with an address.
 */
public class EOEInputData {

    final private String address;

    /**
     * Constructs an {@code EOEInputData} object with the specified address.
     *
     * @param address the address associated with the input data
     */
    public EOEInputData(String address) {
        this.address = address;
    }

    /**
     * Retrieves the address associated with this input data.
     *
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

}
