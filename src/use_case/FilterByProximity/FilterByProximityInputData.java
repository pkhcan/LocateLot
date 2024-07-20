package use_case.FilterByProximity;

import data_access.ParkingLotDAO;
import entity.ParkingLot;

import java.util.List;

public class FilterByProximityInputData {

    private double[] latLong;
    // private List<ParkingLot> parkingLots;

    public FilterByProximityInputData(double[] latLong) {
        // this.parkingLots = parkingLotDAO.getParkingLots();
        this.latLong = latLong;
    }

//    public List<ParkingLot> getParkingLots() {
//        return this.parkingLots;
//    }

    public double[] getLatLong() {
        return this.latLong;
    }

}
