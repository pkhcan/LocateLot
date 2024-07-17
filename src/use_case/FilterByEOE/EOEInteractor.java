package use_case.FilterByEOE;

import entity.Filter;
import entity.ParkingLot;
import entity.EOEFilter;

import use_case.FilterOutput.FilterOutputData;

/**
 * Interactor for handling Ease of Entry (EOE) input data and processing.
 */
public class EOEInteractor implements EOEInputBoundary{

    private final EOEOutputBoundary outputBoundary;

    /**
     * Constructs an {@code EOEInteractor} with the specified output boundary.
     *
     * @param outputBoundary the output boundary for presenting EOE output data
     */
    public EOEInteractor(EOEOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }


    /**
     * Executes the EOE interactor logic based on the provided input data.
     *
     * <p>Steps:
     * - Retrieve filtered parking lots from {@code FilterOutputData}.
     * - Apply {@code EOEFilter} to filter parking lots based on entry reviews.
     * - Prepare {@code EOEOutputData} with filtered parking lots.
     * - Present the prepared output data using {@code outputBoundary}.
     *
     * @param eoeInputData the input data containing EOE details
     */
    public void execute(EOEInputData eoeInputData) {
        // receive output code from default proximity filter (identical ratings are sorted further by proximity)
        // Assume FilterOutputData.getFilteredParkingLots() gives us the parking lots to be sorted:
        // TODO - include code from proximity use case to fetch the parking lots viable within radius
        ParkingLot[] parkingLots = FilterOutputData.getFilteredParkingLots();

        Filter entryFilter = new EOEFilter();
        entryFilter.filter(parkingLots);

        // Prepare output data
        EOEOutputData outputData = new EOEOutputData(parkingLots);

        // Present output data
        outputBoundary.present(outputData);
        }

}
