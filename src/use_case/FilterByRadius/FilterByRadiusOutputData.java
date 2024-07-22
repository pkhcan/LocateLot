package use_case.FilterByRadius;

import entity.ParkingLot;

import java.util.List;

public class FilterByRadiusOutputData {

    private List<ParkingLot> parkingLots;

    /**
     * Output data for filter by radius use case
     * @param parkingLots filtered
     */

    public FilterByRadiusOutputData(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    /**
     * Filter by radius output data getter method
     * @return filtered parking lots based on radius
     */

    public List<ParkingLot> getParkingLots() {
        return this.parkingLots;
    }
}
