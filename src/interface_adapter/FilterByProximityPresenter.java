package interface_adapter;

import app.gui.GUI;
import use_case.FilterByProximity.FilterByProximityOutputBoundary;
import use_case.FilterByProximity.FilterByProximityOutputData;


/**
 * FilterByProximityPresenter class
 * Presents data to GUI
 */
public class FilterByProximityPresenter implements FilterByProximityOutputBoundary {

    /**
     * Constructor method
     * @param gui GUI
     */
    GUI gui;
    public FilterByProximityPresenter(GUI gui) {
        this.gui = gui;
    }

    /**
     * Requests GUI to display the filtered list passed from the interactor
     * @param filterByProximityOutputData outputdata passed from interactor
     */
    @Override
    public void prepareSuccessView(FilterByProximityOutputData filterByProximityOutputData) {
        gui.updateParkingLotList(filterByProximityOutputData.getFilteredByProximity());
    }

    /**
     * Displays a user friendly error message
     * @param message given from interactor
     */
    public void prepareFailView(String message) {
        System.out.println(message);
    }
}
