package use_case.FilterOutput;
import entity.ParkingLot;

import java.util.ArrayList;

public class FilterOutputData {

    private final ParkingLot[] filteredParkingLots;

    /**
     * Construct a FilterOutputData object
     * @param results Takes an array of ParkingLots
     */
    public FilterOutputData(ParkingLot[] results) {
        this.filteredParkingLots = results;
    }

    /**
     * @return an array of ParkingLots in sorted order.
     */
    public ParkingLot[] getFilteredParkingLots() {return sortPL(this.filteredParkingLots);}

    /**
     *
     * @param parkingLots an unsorted array of parking lots
     * @return a sorted version of the given list of parking lots
     */
    private ParkingLot[] sortPL(ParkingLot[] parkingLots) {

    }
}
