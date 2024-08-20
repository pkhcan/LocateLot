package app.gui;

import interface_adapter.FilterByRadiusController;
import interface_adapter.FilterByRadiusPresenter;
import interface_adapter.FilterByRadiusViewModel;
import use_case.FilterByRadius.FilterByRadiusInputBoundary;
import use_case.FilterByRadius.FilterByRadiusInteractor;
import use_case.FilterByRadius.FilterByRadiusOutputBoundary;
import views.FilterByRadiusView;

import javax.swing.*;

public class FilterByRadiusUseCaseFactory {
    GUI gui;
    FilterByRadiusController filterByRadiusController;
    FilterByRadiusViewModel filterByRadiusViewModel;
    FilterByRadiusInputBoundary filterByRadiusInteractor;
    FilterByRadiusOutputBoundary filterByRadiusPresenter;
    JTextField textFieldAddress;

    /**
     * A factory class to separate object instantiation logic from the UI.
     * @param gui
     * @param textFieldAddress
     */
    public FilterByRadiusUseCaseFactory(GUI gui, JTextField textFieldAddress) {
        this.gui = gui;
        this.textFieldAddress = textFieldAddress;
        this.filterByRadiusViewModel = new FilterByRadiusViewModel();
        this.filterByRadiusPresenter = new FilterByRadiusPresenter(filterByRadiusViewModel);
        this.filterByRadiusInteractor = null;
        try {
            filterByRadiusInteractor = new FilterByRadiusInteractor(filterByRadiusPresenter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.filterByRadiusController = new FilterByRadiusController(filterByRadiusInteractor);
    }

    /**
     * Create a new FilterByRadiusView object.
     * @return FilterByRadiusView
     */
    public FilterByRadiusView createFilterByRadiusView() {
        return new FilterByRadiusView(filterByRadiusController, filterByRadiusViewModel, gui, textFieldAddress);
    }
}
