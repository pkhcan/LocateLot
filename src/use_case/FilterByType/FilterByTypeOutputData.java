package use_case.FilterByType;

import entity.ParkingLot;
import java.util.List;


public class FilterByTypeOutputData {
    private List<ParkingLot> parkingLots;

    public FilterByTypeOutputData(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public List<ParkingLot> getParkingLots() {
        return this.parkingLots;
    }

}
