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

public class FilterByRadiusInteractor implements FilterByRadiusInputBoundary {

    public GreenPDAO parkingLotDAO;
    public GeoApiDAO geoApiDAO;
    FilterByRadiusInputData filterByRadiusInputData;
    FilterByRadiusOutputBoundary filterByRadiusPresenter;
    FilterByRadiusOutputData filterByRadiusOutputData;
    public List<ParkingLot> filteredByRadius;

    public FilterByRadiusInteractor(FilterByRadiusOutputBoundary filterByRadiusPresenter) throws IOException {

        this.parkingLotDAO = new ParkingLotDAO();
        this.geoApiDAO = new GeoApiDAO();
        this.filterByRadiusPresenter = filterByRadiusPresenter;
        this.filteredByRadius = new ArrayList<ParkingLot>();

    }

    @Override
    public void execute(FilterByRadiusInputData filterByRadiusInputData) {

        try {
            GeocodingResult[] result = GeoApiDAO.getLatitudeLongitude(filterByRadiusInputData.getAddress());
            double latitude = result[0].geometry.location.lat;
            double longitude = result[0].geometry.location.lng;
            double radius = filterByRadiusInputData.getRadius();

            RadiusFilter filter = new RadiusFilter();
            filteredByRadius = filter.filter(radius, latitude, longitude,
                    parkingLotDAO.getClosestParkingLots(latitude, longitude, parkingLotDAO.getParkingLots()));
            FilterByRadiusOutputData filterByRadiusOutputData = new FilterByRadiusOutputData(filteredByRadius);
            filterByRadiusPresenter.prepareSuccessView(filterByRadiusOutputData);
        } catch (Exception e) {
            System.out.println("Geocoding error");
            System.out.println(e.getMessage());
        }
    }
}



