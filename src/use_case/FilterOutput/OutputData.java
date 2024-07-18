package use_case.FilterOutput;

import entity.ParkingLot;

/**
 * Represents the output data containing sorted parking lots.
 */
public class OutputData {

    private final ParkingLot[] sortedParkingLots;

    /**
     * Constructs an object with the specified sorted parking lots.
     *
     * @param sortedParkingLots the sorted array of parking lots
     */
    public OutputData(ParkingLot[] sortedParkingLots) {
        this.sortedParkingLots = sortedParkingLots;
    }

    /**
     * Retrieves the sorted array of parking lots.
     *
     * @return the sorted parking lots
     */
    public ParkingLot[] getSortedParkingLots() {
        return sortedParkingLots;

    }

}
