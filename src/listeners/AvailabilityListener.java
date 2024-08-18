package listeners;

import app.gui.GUI;
import com.google.maps.errors.ApiException;
import interface_adapter.EOFPresenter;
import use_case.FilterByEOF.EOFInputData;
import use_case.FilterByEOF.EOFInteractor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AvailabilityListener {
    GUI gui;

    public AvailabilityListener(GUI gui) {
        this.gui = gui;
    }

    public ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = gui.getAddress();
                EOFInputData inputData = new EOFInputData(address);

                // Create the presenter
                EOFPresenter presenter = new EOFPresenter(gui);

                // Create the interactor with the presenter
                EOFInteractor interactor = new EOFInteractor(presenter);

                // Execute the interactor
                try {
                    interactor.execute(inputData);
                } catch (IOException | InterruptedException | ApiException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }
}
