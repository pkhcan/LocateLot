package listeners;

import app.gui.FilterByProximityUseCaseFactory;
import app.gui.FilterByRadiusUseCaseFactory;
import app.gui.GUI;
import interface_adapter.FilterByProximityPresenter;
import use_case.FilterByProximity.FilterByProximityInputBoundary;
import use_case.FilterByProximity.FilterByProximityInputData;
import use_case.FilterByProximity.FilterByProximityInteractor;
import use_case.FilterByProximity.FilterByProximityOutputBoundary;
import views.FilterByProximityView;
import views.FilterByRadiusView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ProximityListener {

    /**
     * Listener object for the proximity filter
     */
    private JPanel inputPanel;
    private GUI gui;
    private JTextField textFieldAddress;

    public ProximityListener(JPanel inputPanel, JTextField textFieldAddress, GUI gui) {
        this.inputPanel = inputPanel;
        this.textFieldAddress = textFieldAddress;
        this.gui = gui;
    }

    /**
     * Returns a new action listener for a proximity filter event
     * @return
     */
    public ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                inputPanel.removeAll();
                // to fix a null exception caused by IntelliJ's GUI creator
                inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

                // Object creation encapsulated from UI and View logic using factory design pattern
                FilterByProximityUseCaseFactory filterByProximityUseCaseFactory = new
                        FilterByProximityUseCaseFactory(gui, textFieldAddress);
                FilterByProximityView filterByProximityView = filterByProximityUseCaseFactory.createFilterByProximityView();

                inputPanel.add(filterByProximityView);

                inputPanel.revalidate();
            }

        };
    }
}
