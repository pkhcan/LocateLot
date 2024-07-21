package use_case.FilterByRadius;

import data_access.ParkingLotDAO;
import entity.ParkingLot;
import java.util.ArrayList;
import java.util.List;

public class FilterByRadiusInputData {

    private final int radius;
    private ParkingLotDAO parkingLotDAO;
//    private final double[] latLong;
    private String address;

    public FilterByRadiusInputData(int radius, String address) {
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

    public int getRadius() {
        return radius;
    }
}
