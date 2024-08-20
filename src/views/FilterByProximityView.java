package views;

import app.gui.GUI;
import interface_adapter.FilterByProximityController;
import interface_adapter.FilterByProximityViewModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FilterByProximityView extends JPanel implements PropertyChangeListener {
    private FilterByProximityController filterByProximityController;
    private FilterByProximityViewModel filterByProximityViewModel;
    private GUI gui;
    private JTextField textFieldAddress;

    /**
     * FilterByProximityView class creates proximity filter view to be added to GUI.
     * @param filterByProximityController
     * @param filterByProximityViewModel
     * @param gui
     * @param textFieldAddress
     */
    public FilterByProximityView(FilterByProximityController filterByProximityController,
                                 FilterByProximityViewModel filterByProximityViewModel, GUI gui,
                                 JTextField textFieldAddress) {
        this.filterByProximityController = filterByProximityController;
        this.filterByProximityViewModel = filterByProximityViewModel;
        this.gui = gui;
        this.textFieldAddress = textFieldAddress;
        this.filterByProximityViewModel.addPropertyChangeListener(this);

        showFilter();
    }

    /**
     * Create and add "filter by proximity" view.
     */

    private void showFilter() {

        JLabel labelProximity = new JLabel("Now showing parking lots filtered based on proximity.");

        String address = textFieldAddress.getText();
        try {
            // Send parsed information to back-end
            filterByProximityController.execute(address);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        add(labelProximity);
    }

    /**
     * Updates GUI or displays error message when a property changes.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("parkingLots".equals(evt.getPropertyName())) {
            // update gui
            gui.updateParkingLotList(filterByProximityViewModel.getParkingLots());
        } else if ("errorMessage".equals(evt.getPropertyName())) {
            JOptionPane.showMessageDialog(this, filterByProximityViewModel.getErrorMessage());
        }
    }
}