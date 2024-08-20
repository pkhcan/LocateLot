package app.gui;

import interface_adapter.*;
import use_case.FilterByProximity.FilterByProximityInputBoundary;
import use_case.FilterByProximity.FilterByProximityInteractor;
import use_case.FilterByProximity.FilterByProximityOutputBoundary;
import views.FilterByProximityView;

import javax.swing.*;

public class FilterByProximityUseCaseFactory {
    GUI gui;
    FilterByProximityController filterByProximityController;
    FilterByProximityViewModel filterByProximityViewModel;
    FilterByProximityInputBoundary filterByProximityInteractor;
    FilterByProximityOutputBoundary filterByProximityPresenter;
    JTextField textFieldAddress;

    /**
     * A factory class to separate object instantiation logic from the UI.
     * @param gui
     * @param textFieldAddress
     */
    public FilterByProximityUseCaseFactory(GUI gui, JTextField textFieldAddress) {
        this.gui = gui;
        this.textFieldAddress = textFieldAddress;
        this.filterByProximityViewModel = new FilterByProximityViewModel();
        this.filterByProximityPresenter = new FilterByProximityPresenter(filterByProximityViewModel);
        this.filterByProximityInteractor = null;
        try {
            filterByProximityInteractor = new FilterByProximityInteractor(filterByProximityPresenter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.filterByProximityController = new FilterByProximityController(filterByProximityInteractor);
    }

    /**
     * Create a new FilterByRadiusView object.
     * @return FilterByRadiusView
     */
    public FilterByProximityView createFilterByProximityView() {
        return new FilterByProximityView(filterByProximityController, filterByProximityViewModel, gui, textFieldAddress);
    }
}
