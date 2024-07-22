package interface_adapter;

import use_case.FilterByRadius.FilterByRadiusOutputBoundary;
import app.gui.GUI;
import use_case.FilterByRadius.FilterByRadiusOutputData;

public class FilterByRadiusPresenter implements FilterByRadiusOutputBoundary {
    /**
     * filter by radius presenter class implements filter by radius output boundary. presents data
     * to the GUI.
     */
    private final GUI gui;

    /**
     * Constructor method
     * @param gui GUI
     */
    public FilterByRadiusPresenter(GUI gui) {
        this.gui = gui;
    }

    /**
     * Passes output data (filtered list) to GUI.
     * @param filterByRadiusOutputData contains list of parking lots filtered by radius and proximity
     */
    @Override
    public void prepareSuccessView(FilterByRadiusOutputData filterByRadiusOutputData) {
        // update GUI
        gui.updateParkingLotList(filterByRadiusOutputData.getParkingLots()); // why is gui type ParkingLot[]


    }

    /**
     * prints an error message when called
     * @param message error message
     */
    public void prepareFailView(String message) {
        System.out.println(message);
    }
}
