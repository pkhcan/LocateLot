package listeners;

import app.gui.GUI;
import interface_adapter.FilterByTypePresenter;
import use_case.FilterByType.FilterByTypeInputBoundary;
import use_case.FilterByType.FilterByTypeInputData;
import use_case.FilterByType.FilterByTypeInteractor;
import use_case.FilterByType.FilterByTypeOutputBoundary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TypeListener {
    GUI gui;

    public TypeListener(GUI gui) {
        this.gui = gui;
    }

    public ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = gui.getAddress();
                String type = gui.getAddress();
                FilterByTypeInputData inputData = new FilterByTypeInputData(address);

                FilterByTypeOutputBoundary presenter = new FilterByTypePresenter(gui);

                FilterByTypeInputBoundary interactor = null;
                try {
                    interactor = new FilterByTypeInteractor(presenter);
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
