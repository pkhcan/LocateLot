package interface_adapter;

import entity.ParkingLot;
import use_case.FilterByType.FilterByTypeOutputBoundary;
import app.gui.GUI;
import use_case.FilterByType.FilterByTypeOutputData;

import java.util.List;

public class FilterByTypePresenter implements FilterByTypeOutputBoundary {
    /**
     * Filter by type presenter class implements filter by type output boundary. Presents data
     * to the GUI.
     */
    private final GUI gui;

    /**
     * Constructor method
     * @param gui GUI
     */
    public FilterByTypePresenter(GUI gui) {
        this.gui = gui;
    }

    /**
     * Passes output data (filtered list) to GUI.
     * @param filterByTypeOutputData contains list of parking lots filtered by type
     */
    @Override
    public void prepareSuccessView(FilterByTypeOutputData filterByTypeOutputData) {
        // update GUI
        List<ParkingLot> sortedParkingLots = filterByTypeOutputData.getParkingLots();
        gui.updateParkingLotList(filterByTypeOutputData.getParkingLots());
    }

    /**
     * Prints an error message when called
     * @param message error message
     */
    @Override
    public void prepareFailView(String message) {
        System.out.println(message);
    }
}
