package use_case.FilterByEOE;

import entity.Filter;
import entity.ParkingLot;
import entity.EOEFilter;
import use_case.FilterOutput.FilterOutputData;


public class EOEInteractor implements EOEInputBoundary{

    private final EOEOutputBoundary outputBoundary;

    public EOEInteractor(EOEOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

//    - retrieve all ParkingLot objects that are to be displayed
//    - use getEntryReview method to collect all their ratings
//    - create a list of indices of all the unrated ones - they return "No reviews yet"
//    - sort the rest in descending order and store their indices in an array in their respective order
//    - concatenate with the unrated list - unrated goes at the end
//    - sort input list of parking objects in the order of new list indices


    public void execute(EOEInputData eoeInputData) {
        // receive output code from default proximity filter (identical ratings are sorted further by proximity)
        // Assume FilterOutputData.getFilteredParkingLots() gives us the parking lots to be sorted:
        ParkingLot[] parkingLots = FilterOutputData.getFilteredParkingLots();

        Filter entryFilter = new EOEFilter();
        entryFilter.filter(parkingLots);

        // Prepare output data
        EOEOutputData outputData = new EOEOutputData(parkingLots);

        // Present output data
        outputBoundary.present(outputData);
        }

}
