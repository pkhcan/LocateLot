package use_case.FilterByProximity;

import data_access.ParkingLotDAO;
import entity.Filter;
import entity.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class FilterByProximityInteractor implements FilterByProximityInputBoundary{
    /**
     * Filter by proximity use case interactor. Implements default proximity filter.
     */
    final ParkingLotDAO parkingLotDAO;
    final FilterByProximityInputData inputData;
    final FilterByProximityOutputBoundary filterByProximityPresenter;
    private List<ParkingLot> filteredByProximity;

    public FilterByProximityInteractor(ParkingLotDAO parkingLotDAO,
                                       FilterByProximityInputData filterByProximityInputData,
                                       FilterByProximityOutputBoundary filterByProximityPresenter) {

        this.parkingLotDAO = parkingLotDAO;
        this.inputData = filterByProximityInputData;
        this.filterByProximityPresenter = filterByProximityPresenter;
        this.filteredByProximity = new ArrayList<>();

    }

    @Override
    public FilterByProximityOutputData execute(FilterByProximityInputData filterByProximityInputData) {
        // TODO: Prepare Fail View?
        List<ParkingLot> parkingLots = parkingLotDAO.getParkingLots();
        // input data should get parkingLots from a new DAO instance so that the getClosest method from DAO can work

        while (!parkingLots.isEmpty()) {
            ParkingLot closest = parkingLotDAO.getClosestParkingLot(filterByProximityInputData.getLatLong()[0],
                    filterByProximityInputData.getLatLong()[1]);
            parkingLots.remove(closest);
            filteredByProximity.add(closest);
        }

        // TODO: Create and return OutputBoundary Object (implement OutputBoundary and OutputData objects)
        // TODO: Prepare Success View?

        return null;

    }
}
