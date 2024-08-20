package use_case.FilterByPrice;

import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import data_access.GeoApiDAO;
import data_access.ParkingLotDAO;
import entity.ParkingLot;
import entity.PriceFilter;


import java.io.IOException;
import java.util.List;

/**
 * Interactor for handling Filter By Price input data and processing.
 * Retrieves geolocation data for a given address, finds the parking lots within a 3km radius, applies a
 * filter by price filter where parking lots are sorted from least expensive to most expensive, and presents
 * the results via the output boundary.
 */
public class FilterByPriceInteractor implements FilterByPriceInputBoundary {
    private final FilterByPriceOutputBoundary outputBoundary;

    /**
     * Constructs an {@code FilterByPriceInteractor} with the specified output boundary.
     *
     * @param outputBoundary the output boundary for presenting FilterByPriceInteractor output data
     */
    public FilterByPriceInteractor(FilterByPriceOutputBoundary outputBoundary) {
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
     * @param inputData the input data containing price filter details
     * @throws IOException              I/O error
     * @throws InterruptedException     operation interrupted
     * @throws ApiException             GeoAPI request error
     */
    public void execute(FilterByPriceInputData inputData) throws IOException, InterruptedException, ApiException {

        // Get lat, long of inputted address
        String address = inputData.getAddress();

        try{
            GeocodingResult[] results = GeoApiDAO.getLatitudeLongitude(address);

            if (results == null || results.length == 0) {
                outputBoundary.prepareFailView("No results found for the given address. Please check the address and try again.");
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

        // filter all parking lots based on default radius of 3km and the address inputted
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        List<ParkingLot> allParkingLots = parkingLotDAO.getParkingLots();
        List<ParkingLot> parkingLots = parkingLotDAO.getParkingLotsWithinRadius(latitude, longitude, allParkingLots);

        // sort the radius filtered lots by increasing price
        int userHour = inputData.getTime();
        PriceFilter priceFilter = new PriceFilter();
        priceFilter.sort(parkingLots, userHour);

        // prepare output data
        FilterByPriceOutputData outputData = new FilterByPriceOutputData(parkingLots);
        outputBoundary.prepareSuccessView(outputData);
        } catch (Exception e) {
            outputBoundary.prepareFailView("An error occurred while trying to find the location. Please try again later.");
        }
    }


}