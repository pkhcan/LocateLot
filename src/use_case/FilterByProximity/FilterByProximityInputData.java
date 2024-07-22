package use_case.FilterByProximity;

import data_access.ParkingLotDAO;
import entity.ParkingLot;

import java.util.List;

public class FilterByProximityInputData {

    private String address;
    // private List<ParkingLot> parkingLots;

    public FilterByProximityInputData(String address) {
        // this.parkingLots = parkingLotDAO.getParkingLots();
        this.address = address;
    }

//    public List<ParkingLot> getParkingLots() {
//        return this.parkingLots;
//    }

    public String getAddress() {
        return this.address;
    }

}
