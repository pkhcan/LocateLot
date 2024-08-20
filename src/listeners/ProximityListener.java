package listeners;

import app.gui.GUI;
import interface_adapter.FilterByProximityPresenter;
import use_case.FilterByProximity.FilterByProximityInputBoundary;
import use_case.FilterByProximity.FilterByProximityInputData;
import use_case.FilterByProximity.FilterByProximityInteractor;
import use_case.FilterByProximity.FilterByProximityOutputBoundary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ProximityListener {
    GUI gui;

    public ProximityListener(GUI gui) {
        this.gui = gui;
    }

    public ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = gui.getAddress();
                FilterByProximityInputData inputData = new FilterByProximityInputData(address);

                // Create the presenter
                FilterByProximityOutputBoundary presenter = new FilterByProximityPresenter(gui);

                // Create the interactor with the presenter
                FilterByProximityInputBoundary interactor = null;
                try {
                    interactor = new FilterByProximityInteractor(presenter);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                // Execute the interactor
                try {
                    interactor.execute(inputData);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }
}
