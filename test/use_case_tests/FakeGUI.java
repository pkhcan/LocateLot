package use_case_tests;

import app.gui.GUI;
import entity.ParkingLot;

/**
 * Fake GUI class for testing the Presenter.
 * Extends app.gui.GUI class to capture parking lot list updates.
 */
public class FakeGUI extends GUI {
    /**
     * Array to store parking lots displayed by the GUI.
     */
    public ParkingLot[] displayedParkingLots = null;

    /**
     * Overrides method in app.gui.GUI to capture parking lots that are displayed.
     *
     * @param parkingLots the array of parking lots to display
     */
    @Override
    public void updateParkingLotList(ParkingLot[] parkingLots) {
        displayedParkingLots = parkingLots;
    }
}
