package use_case.FilterByProximity;

import entity.ParkingLot;

import java.util.List;

/**
 * Filter by proximity output data
 */
public class FilterByProximityOutputData {

    private List<ParkingLot> filteredByProximity;

    /**
     * Constructor method
     * @param parkingLots
     */
    public FilterByProximityOutputData(List<ParkingLot> parkingLots) {
        this.filteredByProximity = parkingLots;
    }

    /**
     * getter method for filter by proximity outputdata
     * @return
     */
    public List<ParkingLot> getFilteredByProximity() {
        return filteredByProximity;
    }

}
