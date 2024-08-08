package use_case.FilterByPrice;

import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import data_access.GeoApiDAO;
import data_access.ParkingLotDAO;
import entity.ParkingLot;
import entity.PriceFilter;

import java.io.IOException;
import java.util.List;


public class FilterByPriceInteractor implements FilterByPriceInputBoundary {
    private final FilterByPriceOutputBoundary outputBoundary;

    public FilterByPriceInteractor(FilterByPriceOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(FilterByPriceInputData inputData) throws IOException, InterruptedException, ApiException {

        // Get lat, long of inputted address
        String address = inputData.getAddress();
        GeocodingResult[] results = GeoApiDAO.getLatitudeLongitude(address);

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
    }


}