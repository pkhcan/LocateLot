package use_case.FilterByEOE;

import entity.ParkingLot;

public class EOEOutputData {

    private final ParkingLot[] sortedParkingLots;


    public EOEOutputData(ParkingLot[] sortedParkingLots) {
        this.sortedParkingLots = sortedParkingLots;
    }


    public ParkingLot[] getSortedParkingLots() {
        return sortedParkingLots;

    }

}
