package use_case.FilterByPrice;

import entity.ParkingLot;

import java.util.List;

public class FilterByPriceOutputData {
    private List<ParkingLot> parkingLots;

    /**
     * Output data for filter by price use case
     * @param parkingLots filtered
     */

    public FilterByPriceOutputData(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    /**
     * Filter by radius output data getter method
     * @return filtered parking lots based on price
     */

    public List<ParkingLot> getParkingLots() {
        return this.parkingLots;
    }
}
