package use_case.FilterByRadius;

import data_access.ParkingLotDAO;
import entity.ParkingLot;
import use_case.FilterByProximity.FilterByProximityInteractor;

import java.util.ArrayList;
import java.util.List;

public class FilterByRadiusInteractor implements FilterByRadiusInputBoundary {

    ParkingLotDAO parkingLotDAO;
    FilterByRadiusInputData filterByRadiusInputData;
    FilterByRadiusOutputBoundary filterByRadiusPresenter;
    List<ParkingLot> filteredByRadius;

    public FilterByRadiusInteractor(ParkingLotDAO parkingLotDAO, FilterByRadiusInputData filterByRadiusInputData,
                                    FilterByRadiusOutputBoundary filterByRadiusPresenter) {

        this.parkingLotDAO = parkingLotDAO;
        this.filterByRadiusInputData = filterByRadiusInputData;
        this.filterByRadiusPresenter = filterByRadiusPresenter;
        this.filteredByRadius = new ArrayList<>();

    }

    @Override
    public void execute(FilterByRadiusInputData filterByRadiusInputData) {
        int radius = filterByRadiusInputData.getRadius();
        double[] latLong = filterByRadiusInputData.getLatLong();

        for (ParkingLot parkingLot : filterByRadiusInputData.getParkingLots()) {
            double[] latLongLot = parkingLot.getLatitudeLongitude();
            double distance = Math.hypot(latLong[0] - latLongLot[0], latLong[1] - latLongLot[1]);
            if (distance <= radius) {
                filteredByRadius.add(parkingLot);
            }
        }
    }

}
