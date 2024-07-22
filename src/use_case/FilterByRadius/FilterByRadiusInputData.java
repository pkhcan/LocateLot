package use_case.FilterByRadius;

import data_access.ParkingLotDAO;

public class FilterByRadiusInputData {

    private final double radius;
    private ParkingLotDAO parkingLotDAO;
//    private final double[] latLong;
    private String address;

    public FilterByRadiusInputData(double radius, String address) {
        /**
         * If user input does not specify radius (default int = 0) let the default radius be 3km.
         */
        if (radius != 0) {
            this.radius = radius;
        }
        else {
            this.radius = 3;
        }
//        this.parkingLots = parkingLotDAO.getParkingLots();
        this.address = address;
    }

//    public List<ParkingLot> getParkingLots() {
//        return parkingLots;
//    }

    public String getAddress() {
        return this.address;
    }

    public double getRadius() {
        return radius;
    }
}
