package use_case.FilterByRadius;

import entity.ParkingLot;

import java.util.List;

public class FilterByRadiusOutputData {

    List<ParkingLot> parkingLots;

    public FilterByRadiusOutputData(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public List<ParkingLot> getParkingLots() {
        return this.parkingLots;
    }
}
