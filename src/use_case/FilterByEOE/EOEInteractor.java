package use_case.FilterByEOE;

import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import data_access.ParkingLotDAO;
import data_access.GeoApiDAO;

import entity.Filter;
import entity.ParkingLot;
import entity.EOEFilter;

import use_case.FilterOutput.OutputBoundary;
import use_case.FilterOutput.OutputData;

import java.io.IOException;
import java.util.*;


/**
 * Interactor for handling Ease of Entry (EOE) input data and processing.
 * Retrieves geolocation data for a given address, finds the closest parking lots,
 * applies an EOE filter, and presents the results via the output boundary.
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
     * - Retrieve filtered parking lots from FilterOutputData
     * - Apply EOEFilter to filter parking lots based on entry reviews.
     * - Prepare EOEOutputData with filtered parking lots.
     * - Present the prepared output data using outputBoundary.
     *
     * @param eoeInputData the input data containing EOE details
     * @throws IOException              I/O error
     * @throws InterruptedException     operation interrupted
     * @throws ApiException             GeoAPI request error
     */
    public void execute(EOEInputData eoeInputData) throws IOException, InterruptedException, ApiException {
        String address = eoeInputData.getAddress();
        try {
            GeocodingResult[] results = GeoApiDAO.getLatitudeLongitude(address);

            // No results found
            if (results == null || results.length == 0) {
                outputBoundary.presentError("No results found for the given address. Please check the address and try again.");
                return;
            }

            try {
                results = GeoApiDAO.getLatitudeLongitude(address);
                if (results == null || results.length == 0) {
                    throw new RuntimeException("No geocoding results found for the address.");
                }
            } catch (ApiException | InterruptedException e) {
                throw new RuntimeException("Failed to get latitude and longitude for the address: " + e.getMessage(), e);
            }

            double latitude = results[0].geometry.location.lat;
            double longitude = results[0].geometry.location.lng;

            ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
            List<ParkingLot> allParkingLots = parkingLotDAO.getParkingLots();

            // RadiusFilter to filter parking lots based on the radius -- TODO edit to use radius entity method
            List<ParkingLot> filteredParkingLots = parkingLotDAO.getParkingLotsWithinRadius(latitude, longitude, allParkingLots);

            // EOEFilter on filtered list
            ParkingLot[] parkingLots = filteredParkingLots.toArray(new ParkingLot[0]);

            Filter entryFilter = new EOEFilter();
            entryFilter.filter(parkingLots);

            // Prepare output data
            OutputData outputData = new OutputData(parkingLots);

            // Present output data
            outputBoundary.present(outputData);
        } catch (Exception e) {
            outputBoundary.presentError("An error occurred while trying to find the location. Please try again later.");
        }
    }
}

