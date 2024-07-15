package use_case.FilterByProximity;

import data_access.ParkingLotDAO;
import entity.ParkingLot;

import java.util.List;

public class FilterByProximityInputData {

    private float[] latLong;
    // private List<ParkingLot> parkingLots;

    public FilterByProximityInputData(float[] latLong) {
        // this.parkingLots = parkingLotDAO.getParkingLots();
        this.latLong = latLong; // I think we're using floats some places and doubles other places...
    }

//    public List<ParkingLot> getParkingLots() {
//        return this.parkingLots;
//    }

    public float[] getLatLong() {
        return this.latLong;
    }

}
