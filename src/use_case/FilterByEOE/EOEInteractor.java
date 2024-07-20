package use_case.FilterByEOE;

import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import data_access.ParkingLotDAO;
import entity.Filter;
import entity.ParkingLot;
import entity.EOEFilter;
import data_access.GeoApiDAO;

//import use_case.FilterByRadius.FilterByRadiusInputData;
//import use_case.FilterByRadius.FilterByRadiusInteractor;
//import use_case.FilterByRadius.FilterByRadiusPresenter;
import use_case.FilterOutput.OutputBoundary;
import use_case.FilterOutput.OutputData;

import java.io.IOException;
import java.util.*;

import static data_access.GeoApiDAO.getLatitudeLongitude;


import java.util.ArrayList;

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
        GeocodingResult[] results = getLatitudeLongitude(address);

        double[] latLong;

        if (results != null && results.length > 0) {
            double latitude = results[0].geometry.location.lat;
            double longitude = results[0].geometry.location.lng;
            latLong = new double[]{latitude, longitude};
        } else {
            throw new InterruptedException("Failed to get latitude and longitude for the address.");
        }

        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        List<ParkingLot> allParkingLots = parkingLotDAO.getParkingLots();
        List<ParkingLot> closestParkingLots = new ArrayList<>();

        // get closest parking lots
        while (closestParkingLots.size() < 5 && !allParkingLots.isEmpty()) {
            ParkingLot closest = getClosestParkingLot(latLong[0], latLong[1], allParkingLots);
            if (closest != null) {
                closestParkingLots.add(closest);
                allParkingLots.remove(closest);
            }
        }

        ParkingLot[] parkingLots = closestParkingLots.toArray(new ParkingLot[0]);

        Filter entryFilter = new EOEFilter();
        entryFilter.filter(parkingLots);

        // Prepare output data
        OutputData outputData = new OutputData(parkingLots);

        // Present output data
        outputBoundary.present(outputData);
        }

    private ParkingLot getClosestParkingLot(double latitude, double longitude, List<ParkingLot> parkingLots) {
        ParkingLot closest = null;
        double smallestDistance = Double.MAX_VALUE;

        for (ParkingLot parkingLot : parkingLots) {
            float[] latLong = parkingLot.getLatitudeLongitude();
            double distance = Math.hypot(latLong[0] - latitude, latLong[1] - longitude);

            if (distance < smallestDistance) {
                smallestDistance = distance;
                closest = parkingLot;
            }
        }

        return closest;
    }
}





// receive output code from default proximity filter (identical ratings are sorted further by proximity)
// Assume FilterOutputData.getFilteredParkingLots() gives us the parking lots to be sorted:
// TODO - include code from proximity use case to fetch the parking lots viable within radius
//        ArrayList<ParkingLot> parkingLotArray = ParkingLotDAO.getParkingLots();
//        ParkingLot[] parkingLots = parkingLotArray.toArray(new ParkingLot[0]);
