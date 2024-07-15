package use_case.FilterByRadius;

import data_access.ParkingLotDAO;
import entity.ParkingLot;
import java.util.ArrayList;
import java.util.List;

public class FilterByRadiusInputData {

    private final int radius;
    private List<ParkingLot> parkingLots;
    private final double[] latLong;

    public FilterByRadiusInputData(int radius, double[] latLong, ArrayList<ParkingLot> parkingLots) {
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
        this.latLong = latLong;
        this.parkingLots = parkingLots;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public double[] getLatLong() {
        return latLong;
    }

    public int getRadius() {
        return radius;
    }
}
