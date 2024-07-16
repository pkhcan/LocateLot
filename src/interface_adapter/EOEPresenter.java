package interface_adapter;


import app.gui.GUI;
import entity.ParkingLot;
import use_case.FilterByEOE.EOEOutputBoundary;
import use_case.FilterByEOE.EOEOutputData;

public class EOEPresenter implements EOEOutputBoundary {

    private final GUI gui;

    public EOEPresenter(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void present(EOEOutputData outputData) {
        ParkingLot[] sortedParkingLots = outputData.getSortedParkingLots();
        gui.updateParkingLotList(sortedParkingLots);

    }
}
