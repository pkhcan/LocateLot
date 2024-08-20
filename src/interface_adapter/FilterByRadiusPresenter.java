package interface_adapter;

import entity.ParkingLot;
import use_case.FilterByRadius.FilterByRadiusOutputBoundary;

import use_case.FilterByRadius.FilterByRadiusOutputData;

import java.util.List;

public class FilterByRadiusPresenter implements FilterByRadiusOutputBoundary {
    private final FilterByRadiusViewModel filterByRadiusViewModel;

    /**
     * FilterByRadiusPresenter class prepares success and fail views for the GUI.
     * @param filterByRadiusViewModel
     */
    public FilterByRadiusPresenter(FilterByRadiusViewModel filterByRadiusViewModel) {
        this.filterByRadiusViewModel = filterByRadiusViewModel;
    }

    /**
     * Calls setParkingLots method in view model to update GUI with success view (list of parking lots)
     * @param filterByRadiusOutputData
     */

    @Override
    public void prepareSuccessView(FilterByRadiusOutputData filterByRadiusOutputData) {
        List<ParkingLot> parkingLots = filterByRadiusOutputData.getParkingLots();
        filterByRadiusViewModel.setParkingLots(parkingLots);
    }

    /**
     * Calls setErrorMessage method in view model to display an error message to the GUI.
     * @param errorMessage
     */

    @Override
    public void prepareFailView(String errorMessage) {
        filterByRadiusViewModel.setErrorMessage(errorMessage);
    }
}
