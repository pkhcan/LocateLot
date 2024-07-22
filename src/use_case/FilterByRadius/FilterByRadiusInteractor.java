package use_case.FilterByRadius;

import com.google.maps.errors.ApiException;
import data_access.GreenPDAO;
import data_access.ParkingLotDAO;
import entity.ParkingLot;
import entity.RadiusFilter;
import use_case.FilterByProximity.FilterByProximityInteractor;
import data_access.GeoApiDAO;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import com.google.maps.model.GeocodingResult;

/**
 * Filter by radius use case interactor. Implements filter by radius input boundary.
 */
public class FilterByRadiusInteractor implements FilterByRadiusInputBoundary {

    public GreenPDAO parkingLotDAO;
    public GeoApiDAO geoApiDAO;
    FilterByRadiusInputData filterByRadiusInputData;
    FilterByRadiusOutputBoundary filterByRadiusPresenter;
    FilterByRadiusOutputData filterByRadiusOutputData;
    public List<ParkingLot> filteredByRadius;

    /**
     * Constructor method
     * @param filterByRadiusPresenter
     * @throws IOException
     */

    public FilterByRadiusInteractor(FilterByRadiusOutputBoundary filterByRadiusPresenter) throws IOException {

        this.parkingLotDAO = new ParkingLotDAO();
        this.geoApiDAO = new GeoApiDAO();
        this.filterByRadiusPresenter = filterByRadiusPresenter;
        this.filteredByRadius = new ArrayList<>();

    }

    /**
     * Passes user input from GUI to filter entity, then retrieves output data and sends to presenter
     * @param filterByRadiusInputData contains user input for address and radius
     */
    @Override
    public void execute(FilterByRadiusInputData filterByRadiusInputData) {

        try {
            GeocodingResult[] result = GeoApiDAO.getLatitudeLongitude(filterByRadiusInputData.getAddress());
            /**
             * if statement handles case of invalid address. This could be an incomplete address or spelling
             * error. An invalid address will return an empty GeocodingResult array.
             */
            if (result.length != 0) {

                double latitude = result[0].geometry.location.lat; // current latitude passed to radius filter entity
                double longitude = result[0].geometry.location.lng; // current longitude passed to radius filter entity
                double radius = filterByRadiusInputData.getRadius(); // ideal radius passed to radius filter entity.

                RadiusFilter filter = new RadiusFilter(); // new instance of radius filter entity.
                filteredByRadius = filter.filter(radius, latitude, longitude,
                        parkingLotDAO.getClosestParkingLots(latitude, longitude, parkingLotDAO.getParkingLots()));
                FilterByRadiusOutputData filterByRadiusOutputData = new FilterByRadiusOutputData(filteredByRadius);
                filterByRadiusPresenter.prepareSuccessView(filterByRadiusOutputData);
            }
            else { // if GeocodingResult array is empty and user input for address is therefore invalid
                FilterByRadiusOutputData filterByRadiusOutputData = new FilterByRadiusOutputData(filteredByRadius);
                filterByRadiusPresenter.prepareFailView("No coordinates found for given address. Please ensure that " +
                        "the given address is free of spelling errors and follows the following format, for example: " +
                        "'20 Charles Street East, Toronto, ON, Canada' or '20 Charles Street East'");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            filterByRadiusPresenter.prepareFailView("Geocoding issue. Please try again later.");
        }
    }
}



