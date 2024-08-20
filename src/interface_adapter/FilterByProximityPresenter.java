package interface_adapter;

import app.gui.GUI;
import entity.ParkingLot;
import use_case.FilterByProximity.FilterByProximityOutputBoundary;
import use_case.FilterByProximity.FilterByProximityOutputData;
import use_case.FilterByRadius.FilterByRadiusOutputData;

import java.util.List;


/**
 * FilterByProximityPresenter class
 * Presents data to GUI
 */
public class FilterByProximityPresenter implements FilterByProximityOutputBoundary {

    private final FilterByProximityViewModel filterByProximityViewModel;

    /**
     * FilterByProximityPresenter class prepares success and fail views for the GUI.
     * @param filterByProximityViewModel
     */
    public FilterByProximityPresenter(FilterByProximityViewModel filterByProximityViewModel) {
        this.filterByProximityViewModel = filterByProximityViewModel;
    }

    /**
     * Calls setParkingLots method in view model to update GUI with success view (list of parking lots)
     * @param filterByProximityOutputData
     */

    @Override
    public void prepareSuccessView(FilterByProximityOutputData filterByProximityOutputData) {
        List<ParkingLot> parkingLots = filterByProximityOutputData.getFilteredByProximity();
        filterByProximityViewModel.setParkingLots(parkingLots);
    }

    /**
     * Calls setErrorMessage method in view model to display an error message to the GUI.
     * @param errorMessage
     */

    @Override
    public void prepareFailView(String errorMessage) {
        filterByProximityViewModel.setErrorMessage(errorMessage);
    }
}
