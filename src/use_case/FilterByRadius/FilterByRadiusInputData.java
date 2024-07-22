package use_case.FilterByRadius;

import data_access.ParkingLotDAO;

public class FilterByRadiusInputData {

    private final double radius;
    private ParkingLotDAO parkingLotDAO;
    private String address;

    /**
     * Filter by radius input data for filter by radius use case.
     * @param radius user input
     * @param address user input
     */

    public FilterByRadiusInputData(double radius, String address) {
        /*
         * If user input does not specify radius (default int = 0) let the default radius be 3km.
         */
        if (radius != 0) {
            this.radius = radius;
        }
        else {
            this.radius = 3;
        }

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
     * @return address
     */
    public double getRadius() {
        return radius;
    }
}
