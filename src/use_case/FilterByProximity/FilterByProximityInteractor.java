package use_case.FilterByProximity;

import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import data_access.GeoApiDAO;
import data_access.ParkingLotDAO;
import entity.Filter;
import entity.ParkingLot;
import entity.ProximityFilter;
import use_case.FilterOutput.OutputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilterByProximityInteractor implements FilterByProximityInputBoundary{
    /**
     * Filter by proximity use case interactor. Implements default proximity filter.
     */
    private final ParkingLotDAO parkingLotDAO;
    private final FilterByProximityOutputBoundary filterByProximityPresenter;
    private List<ParkingLot> filteredByProximity;

    public FilterByProximityInteractor(FilterByProximityOutputBoundary filterByProximityPresenter) throws IOException {

        this.parkingLotDAO = new ParkingLotDAO();
        this.filterByProximityPresenter = filterByProximityPresenter;
        this.filteredByProximity = new ArrayList<>();

    }

    @Override
    public void execute(FilterByProximityInputData filterByProximityInputData) {
        List<ParkingLot> parkingLots = parkingLotDAO.getParkingLots();
        // input data should get parkingLots from a new DAO instance so that the getClosest method from DAO can work

        try {
            GeocodingResult[] result = GeoApiDAO.getLatitudeLongitude(filterByProximityInputData.getAddress());
            double latitude = result[0].geometry.location.lat;
            double longitude = result[0].geometry.location.lng;
            filteredByProximity.clear();
            ProximityFilter filter = new ProximityFilter();
            filteredByProximity = filter.filter(latitude, longitude, (ArrayList) parkingLots);
//            filteredByProximity = parkingLotDAO.getClosestParkingLots(latitude, longitude, parkingLotDAO.getParkingLots());

            FilterByProximityOutputData outputData = new FilterByProximityOutputData(filteredByProximity);
            filterByProximityPresenter.prepareSuccessView(outputData);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }


//        while (!parkingLots.isEmpty()) {
//            ParkingLot closest = parkingLotDAO.getClosestParkingLot(filterByProximityInputData.getLatLong()[0],
//                    filterByProximityInputData.getLatLong()[1]);
//            parkingLots.remove(closest);
//            filteredByProximity.add(closest);
//        }

    }
}
