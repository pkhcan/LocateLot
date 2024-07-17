package use_case.FilterByEOE;

import entity.ParkingLot;

/**
 * Represents the output data containing sorted parking lots based on Ease of Entry (EOE) filtering.
 */
public class EOEOutputData {

    private final ParkingLot[] sortedParkingLots;

    /**
     * Constructs an {@code EOEOutputData} object with the specified sorted parking lots.
     *
     * @param sortedParkingLots the sorted array of parking lots based on EOE filtering
     */
    public EOEOutputData(ParkingLot[] sortedParkingLots) {
        this.sortedParkingLots = sortedParkingLots;
    }

    /**
     * Retrieves the sorted array of parking lots based on EOE filtering.
     *
     * @return the sorted parking lots
     */
    public ParkingLot[] getSortedParkingLots() {
        return sortedParkingLots;

    }

}
