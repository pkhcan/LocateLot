package interface_adapter;

import com.google.maps.errors.ApiException;
import data_access.ParkingLotDAO;
import use_case.FilterByPrice.FilterByPriceInputBoundary;
import use_case.FilterByPrice.FilterByPriceInputData;
import java.time.LocalTime;



import java.io.IOException;



public class FilterByPriceController {
    private final FilterByPriceInputBoundary filterByPriceInputBoundary;

    //This creates the interactor
    public FilterByPriceController(FilterByPriceInputBoundary filterByPriceInteractor) {
        this.filterByPriceInputBoundary = filterByPriceInteractor;
    }


    // This creates the input data and passes it to the interactor
    public void PriceFiltering(String address, int time) throws IOException, InterruptedException, ApiException {
        FilterByPriceInputData filterByPriceInputData = new FilterByPriceInputData(address, time);
        filterByPriceInputBoundary.execute(filterByPriceInputData);
    }
}