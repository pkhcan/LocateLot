package use_case.FilterByType;

import data_access.ParkingLotDAO;

public class FilterByTypeInputData {

    private final String type;
    private ParkingLotDAO parkingLotDAO;
    private String address;

    /**
     * Filter by type input data for filter by type use case.
     * @param type user input
     * @param address user input
     */
    public FilterByTypeInputData(String type, String address) {
        this.type = type;
        this.address = address;
    }

    /**
     * Getter method
     * @return address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Getter method
     * @return type
     */
    public String getType() {
        return type;
    }
}
