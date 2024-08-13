package views;

import interface_adapter.FilterByRadiusController;
import interface_adapter.FilterByRadiusViewModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import app.gui.GUI;

public class FilterByRadiusView extends JPanel implements PropertyChangeListener {
    private FilterByRadiusController filterByRadiusController;
    private FilterByRadiusViewModel filterByRadiusViewModel;
    private GUI gui;
    private JTextField textFieldRadius;
    private JTextField textFieldAddress;
    private JButton filterButton;

    /**
     * FilterByRadiusView class creates radius filter view to be added to GUI.
     * @param filterByRadiusController
     * @param filterByRadiusViewModel
     * @param gui
     * @param textFieldAddress
     */
    public FilterByRadiusView(FilterByRadiusController filterByRadiusController,
                              FilterByRadiusViewModel filterByRadiusViewModel, GUI gui, JTextField textFieldAddress) {
        this.filterByRadiusController = filterByRadiusController;
        this.filterByRadiusViewModel = filterByRadiusViewModel;
        this.gui = gui;
        this.textFieldAddress = textFieldAddress;
        this.textFieldRadius = new JTextField(5);
        this.filterByRadiusViewModel.addPropertyChangeListener(this);

        showFilter();
    }

    /**
     * Create and add "filter by radius" view.
     */

    private void showFilter() {

        filterButton = new JButton("Filter");

        JLabel labelRadius = new JLabel("Radius (in km): ");

        filterButton.addActionListener(e -> {
            double radius = Double.parseDouble(textFieldRadius.getText());
            String address = textFieldAddress.getText();
            try {
                // Send parsed information to back-end
                filterByRadiusController.execute(radius, address);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        add(labelRadius);
        add(textFieldRadius);
        add(filterButton);
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
            gui.updateParkingLotList(filterByRadiusViewModel.getParkingLots());
        } else if ("errorMessage".equals(evt.getPropertyName())) {
            JOptionPane.showMessageDialog(this, filterByRadiusViewModel.getErrorMessage());
        }
    }
}
