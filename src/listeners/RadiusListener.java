package listeners;

import app.gui.GUI;
import interface_adapter.FilterByRadiusPresenter;
import use_case.FilterByRadius.FilterByRadiusInputBoundary;
import use_case.FilterByRadius.FilterByRadiusInputData;
import use_case.FilterByRadius.FilterByRadiusInteractor;
import use_case.FilterByRadius.FilterByRadiusOutputBoundary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RadiusListener {
    GUI gui;

    public RadiusListener(GUI gui) {
        this.gui = gui;
    }

    public ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String address = gui.getAddress();
                double radius = 3.0;
                FilterByRadiusInputData inputData = new FilterByRadiusInputData(radius, address);

                // Create the presenter
                FilterByRadiusOutputBoundary presenter = new FilterByRadiusPresenter(gui);

                // Create the interactor with the presenter
                FilterByRadiusInputBoundary interactor = null;
                try {
                    interactor = new FilterByRadiusInteractor(presenter);
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
