package listeners;

import app.gui.FilterByRadiusUseCaseFactory;
import app.gui.GUI;
import interface_adapter.FilterByRadiusPresenter;
import use_case.FilterByRadius.FilterByRadiusInputBoundary;
import use_case.FilterByRadius.FilterByRadiusInputData;
import use_case.FilterByRadius.FilterByRadiusInteractor;
import use_case.FilterByRadius.FilterByRadiusOutputBoundary;
import views.FilterByRadiusView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RadiusListener {
    private JPanel inputPanel;
    private GUI gui;
    private JTextField textFieldAddress;

    /**
     * Listener object for the radius filter
     * @param inputPanel
     * @param textFieldAddress
     * @param gui
     */
    public RadiusListener(JPanel inputPanel, JTextField textFieldAddress, GUI gui) {
        this.inputPanel = inputPanel;
        this.textFieldAddress = textFieldAddress;
        this.gui = gui;
    }

    /**
     * Returns a new action listener
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
                FilterByRadiusUseCaseFactory filterByRadiusUseCaseFactory = new
                        FilterByRadiusUseCaseFactory(gui, textFieldAddress);
                FilterByRadiusView filterByRadiusView = filterByRadiusUseCaseFactory.createFilterByRadiusView();

                inputPanel.add(filterByRadiusView);

                inputPanel.revalidate();
            }

        };
    }
}
