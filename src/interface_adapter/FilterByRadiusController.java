package interface_adapter;

import entity.ParkingLot;
import use_case.FilterByProximity.FilterByProximityInputBoundary;
import use_case.FilterByProximity.FilterByProximityInputData;
import use_case.FilterByRadius.FilterByRadiusInputBoundary;
import use_case.FilterByRadius.FilterByRadiusInputData;
import use_case.FilterByProximity.FilterByProximityOutputData;

import java.util.ArrayList;
import java.util.List;

public class FilterByRadiusController {

    private final FilterByRadiusInputBoundary filterByRadiusInteractor;
    private final FilterByProximityInputBoundary filterByProximityInteractor;

    public FilterByRadiusController(FilterByRadiusInputBoundary filterByRadiusInteractor,
                                    FilterByProximityInputBoundary filterByProximityInteractor) {
        this.filterByRadiusInteractor = filterByRadiusInteractor;
        this.filterByProximityInteractor = filterByProximityInteractor;
    }

    public void execute(int radius, double[] latLong) {
        FilterByProximityInputData filterByProximityInputData = new FilterByProximityInputData(latLong);
        FilterByProximityOutputData filterByProximityOutputData =
                filterByProximityInteractor.execute(filterByProximityInputData);
        // Gives filterByRadiusInteractor a sorted list of parking lots so that filterByRadius only filters by Radius
        // TODO: Implement filterByProximityOutputData
//        List<ParkingLot> parkingLots = filterByProximityOutputData.getParkingLots();
//        FilterByRadiusInputData filterByRadiusInputData = new FilterByRadiusInputData(radius, latLong, parkingLots);
//        filterByRadiusInteractor.execute(filterByRadiusInputData);
    }
}
