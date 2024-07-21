package use_case_tests;

import app.gui.GUI;
import entity.ParkingLot;

/**
 * Fake GUI created to test Presenter
 */
public class FakeGUI extends GUI {
    public ParkingLot[] displayedParkingLots = null;

    @Override
    public void updateParkingLotList(ParkingLot[] parkingLots) {
        displayedParkingLots = parkingLots;
    }
}
