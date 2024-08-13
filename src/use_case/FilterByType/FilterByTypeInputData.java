package use_case.FilterByType;

import data_access.ParkingLotDAO;

public class FilterByTypeInputData {

    private ParkingLotDAO parkingLotDAO;
    private String address;

    /**
     * Filter by type input data for filter by type use case.
     * @param address user input
     */
    public FilterByTypeInputData(String address) {
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
//    public String getType() {
//        return type;
//    }
}
