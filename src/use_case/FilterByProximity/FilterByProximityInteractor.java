package use_case.FilterByProximity;

import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import data_access.GeoApiDAO;
import data_access.ParkingLotDAO;
import entity.ParkingLot;
import entity.ProximityFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Filter by proximity use case interactor.
 */
public class FilterByProximityInteractor implements FilterByProximityInputBoundary{

    private final ParkingLotDAO parkingLotDAO;
    private final FilterByProximityOutputBoundary filterByProximityPresenter;
    private List<ParkingLot> filteredByProximity;

    /**
     * Constructor method
     * @param filterByProximityPresenter
     * @throws IOException
     */

    public FilterByProximityInteractor(FilterByProximityOutputBoundary filterByProximityPresenter) throws IOException {

        this.parkingLotDAO = new ParkingLotDAO();
        this.filterByProximityPresenter = filterByProximityPresenter;
        this.filteredByProximity = new ArrayList<>();

    }

    /**
     * Instantiates new ProximityFilter entity to sort given list from closest to farthest parking lots
     * @param filterByProximityInputData contains user input for address
     */

    @Override
    public void execute(FilterByProximityInputData filterByProximityInputData) {
        List<ParkingLot> parkingLots = parkingLotDAO.getParkingLots(); // list of parking lots to be filtered

        try {
            // get coordinates for user inputted address
            GeocodingResult[] result = GeoApiDAO.getLatitudeLongitude(filterByProximityInputData.getAddress());
            if (result.length > 0) { // handles case of incorrectly formatted address. GeocodingResult array will be empty.
                double latitude = result[0].geometry.location.lat; // to be passed to filter entity
                double longitude = result[0].geometry.location.lng; // to be passed to filter entity
                filteredByProximity.clear(); // ensure that filtered list is empty
                ProximityFilter filter = new ProximityFilter();
                filteredByProximity = filter.filter(latitude, longitude, (ArrayList) parkingLots); // filter parking lots

                FilterByProximityOutputData outputData = new FilterByProximityOutputData(filteredByProximity);
                filterByProximityPresenter.prepareSuccessView(outputData);

            } else {
                filterByProximityPresenter.prepareFailView("No coordinates found for given address. Please ensure that " +
                        "the given address is free of spelling errors and follows the following format: " +
                        "'20 Charles Street East, Toronto, ON, Canada' or '20 Charles Street East'");
            }

        } catch (IOException e) {
            filterByProximityPresenter.prepareFailView("Error occurred. Please try again later.");
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            filterByProximityPresenter.prepareFailView("Error occurred. Please try again later.");
            throw new RuntimeException(e);
        } catch (ApiException e) {
            filterByProximityPresenter.prepareFailView("Error occurred. Please try again later.");
            throw new RuntimeException(e);
        }

    }
}
