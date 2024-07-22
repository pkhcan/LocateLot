package use_case_tests;

import app.gui.GUI;
import entity.ParkingLot;

import java.util.List;

/**
 * Fake GUI created to test Presenter
 */
public class FakeGUI extends GUI {
    public ParkingLot[] displayedParkingLots = null;
    public List<ParkingLot> displayedParkingLotsList = null;

    @Override
    public void updateParkingLotList(ParkingLot[] parkingLots) {
        displayedParkingLots = parkingLots;
    }

    @Override
    public void updateParkingLotList(List<ParkingLot> parkingLots) {
        displayedParkingLotsList = parkingLots;
    }
}
