package use_case.FilterByProximity;

import entity.ParkingLot;

import java.util.List;

public class FilterByProximityOutputData {

    private List<ParkingLot> filteredByProximity;

    public FilterByProximityOutputData(List<ParkingLot> parkingLots) {
        this.filteredByProximity = parkingLots;
    }

    public List<ParkingLot> getFilteredByProximity() {
        return filteredByProximity;
    }

}
