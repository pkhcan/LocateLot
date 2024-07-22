package interface_adapter;

import use_case.FilterByRadius.FilterByRadiusOutputBoundary;
import use_case.FilterByRadius.FilterByRadiusOutputData;
import app.gui.GUI;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FilterByRadiusPresenter implements FilterByRadiusOutputBoundary {
    private final GUI gui;

    public FilterByRadiusPresenter(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void prepareSuccessView(FilterByRadiusOutputData filterByRadiusOutputData) {
        // update GUI
        gui.updateParkingLotList(filterByRadiusOutputData.getParkingLots()); // why is gui type ParkingLot[]

    }
}
