package listeners;

import app.gui.GUI;
import com.google.maps.errors.ApiException;
import interface_adapter.EOEController;
import interface_adapter.EOEPresenter;
import use_case.FilterByEOE.EOEInteractor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EOEListener {
    GUI gui;

    public EOEListener(GUI gui) {
        this.gui = gui;
    }

    public ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create price input data with the address from the text field
                String address = gui.getAddress();

                // Create the presenter
                EOEPresenter presenter = new EOEPresenter(gui);

                // Create the interactor with the presenter
                EOEInteractor interactor = new EOEInteractor(presenter);

                // Create the controller with the interactor
                EOEController controller = new EOEController(interactor);

                // Execute the interactor via the controller - handle price filter request
                try {
                    controller.handleEOE(address);
                } catch (IOException | InterruptedException | ApiException ex) {
                    gui.showError(ex.getMessage());
                }
            }
        };
    }
}
