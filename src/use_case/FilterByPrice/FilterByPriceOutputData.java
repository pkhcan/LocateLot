package use_case.FilterByPrice;

import entity.ParkingLot;

import java.util.List;

public class FilterByPriceOutputData {
    private List<ParkingLot> sortedParkingLots;

    /**
     * Output data for filter by price use case
     * @param sortedParkingLots filtered
     */

    public FilterByPriceOutputData(List<ParkingLot> sortedParkingLots) {
        this.sortedParkingLots = sortedParkingLots;
    }

    /**
     * Filter by radius output data getter method
     * @return filtered parking lots based on price
     */

    public List<ParkingLot> getSortedParkingLots() {
        return this.sortedParkingLots;
    }
}
