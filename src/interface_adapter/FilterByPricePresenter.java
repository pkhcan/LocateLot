package interface_adapter;

import use_case.FilterByPrice.FilterByPriceOutputBoundary;
import app.gui.GUI;
import use_case.FilterByPrice.FilterByPriceOutputData;


public class FilterByPricePresenter implements FilterByPriceOutputBoundary{
    /**
     * filter by radius presenter class implements filter by radius output boundary. presents data
     * to the GUI.
     */
    private final GUI gui;

    /**
     * Constructor method
     * @param gui GUI
     */
    public FilterByPricePresenter(GUI gui) {
        this.gui = gui;
    }

    /**
     * Passes output data (filtered list) to GUI.
     * @param filterByPriceOutputData contains list of parking lots filtered by radius and proximity
     */
    @Override
    public void prepareSuccessView(FilterByPriceOutputData filterByPriceOutputData) {
        // update GUI
        gui.updateParkingLotList(filterByPriceOutputData.getParkingLots()); // why is gui type ParkingLot[]


    }

    /**
     * prints an error message when called
     * @param message error message
     */
    public void prepareFailView(String message) {
        System.out.println(message);
    }
}
