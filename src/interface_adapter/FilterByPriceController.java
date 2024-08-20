package interface_adapter;

import com.google.maps.errors.ApiException;
import data_access.ParkingLotDAO;
import use_case.FilterByPrice.FilterByPriceInputBoundary;
import use_case.FilterByPrice.FilterByPriceInputData;
import java.time.LocalTime;



import java.io.IOException;


/**
 * Controller for the filter by price use case.
 * Acts as an intermediary between the user interface and the filter by price use case interactor.
 */
public class FilterByPriceController {

    /**
     * Constructs an {@code Filter By Price Controller} with the specified interactor.
     *
     * @param interactor the interactor for handling filter by price use case
     */
    private final FilterByPriceInputBoundary interactor;

    public FilterByPriceController(FilterByPriceInputBoundary filterByPriceInteractor) {
        this.interactor = filterByPriceInteractor;
    }

    /**
     * Handles the filter by price use case by creating input data and passing it to the interactor.
     *
     * @param address the address for which to filter parking lots by price
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     * @throws ApiException         if an API error occurs
     */
    public void handlePriceFiltering(String address, int time) throws IOException, InterruptedException, ApiException {
        FilterByPriceInputData filterByPriceInputData = new FilterByPriceInputData(address, time);
        interactor.execute(filterByPriceInputData);
    }
}