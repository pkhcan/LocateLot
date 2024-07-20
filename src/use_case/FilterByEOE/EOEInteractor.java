package use_case.FilterByEOE;

import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import data_access.ParkingLotDAO;
import entity.Filter;
import entity.ParkingLot;
import entity.EOEFilter;
import data_access.GeoApiDAO;

import use_case.FilterByRadius.FilterByRadiusInputData;
import use_case.FilterByRadius.FilterByRadiusInteractor;
import use_case.FilterByRadius.FilterByRadiusPresenter;
import use_case.FilterOutput.OutputBoundary;
import use_case.FilterOutput.OutputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static data_access.GeoApiDAO.getLatitudeLongitude;


/**
 * Interactor for handling Ease of Entry (EOE) input data and processing.
 */
public class EOEInteractor implements EOEInputBoundary{

    private final OutputBoundary outputBoundary;

    /**
     * Constructs an {@code EOEInteractor} with the specified output boundary.
     *
     * @param outputBoundary the output boundary for presenting EOE output data
     */
    public EOEInteractor(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }


    /**
     * Executes the EOE interactor logic based on the provided input data.
     * Steps:
     * - Retrieve filtered parking lots from {@code FilterOutputData}.
     * - Apply {@code EOEFilter} to filter parking lots based on entry reviews.
     * - Prepare {@code EOEOutputData} with filtered parking lots.
     * - Present the prepared output data using {@code outputBoundary}.
     *
     * @param eoeInputData the input data containing EOE details
     */
    public void execute(EOEInputData eoeInputData) throws IOException, InterruptedException, ApiException {
        String address = eoeInputData.getAddress();
        GeocodingResult[] latLong = getLatitudeLongitude(address);

        // receive output code from default proximity filter (identical ratings are sorted further by proximity)
        // Assume FilterOutputData.getFilteredParkingLots() gives us the parking lots to be sorted:
        // TODO - include code from proximity use case to fetch the parking lots viable within radius
        ArrayList<ParkingLot> parkingLotArray = ParkingLotDAO.getParkingLots();
        ParkingLot[] parkingLots = parkingLotArray.toArray(new ParkingLot[0]);

//        ParkingLot[] parkingLots = new ParkingLot[3];
        Filter entryFilter = new EOEFilter();
        entryFilter.filter(parkingLots);

        // Prepare output data
        OutputData outputData = new OutputData(parkingLots);

        // Present output data
        outputBoundary.present(outputData);
        }

}
