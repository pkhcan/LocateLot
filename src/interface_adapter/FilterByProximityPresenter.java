package interface_adapter;

import app.gui.GUI;
import use_case.FilterByProximity.FilterByProximityOutputBoundary;
import use_case.FilterByProximity.FilterByProximityOutputData;

public class FilterByProximityPresenter implements FilterByProximityOutputBoundary {

    GUI gui;
    public FilterByProximityPresenter(GUI gui) {
        this.gui = gui;
    }
    @Override
    public void prepareSuccessView(FilterByProximityOutputData filterByProximityOutputData) {
        gui.updateParkingLotList(filterByProximityOutputData.getFilteredByProximity());
    }
}
