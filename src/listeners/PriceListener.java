package listeners;

import app.gui.GUI;
import interface_adapter.FilterByPriceController;
import interface_adapter.FilterByPricePresenter;
import use_case.FilterByPrice.FilterByPriceInputData;
import use_case.FilterByPrice.FilterByPriceInteractor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class PriceListener {
    GUI gui;

    public PriceListener(GUI gui) {
        this.gui = gui;
    }

    public ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String address = gui.getAddress();
                int currentTime = LocalTime.now().getHour();

                FilterByPricePresenter presenter = new FilterByPricePresenter(gui);
                FilterByPriceInteractor interactor = new FilterByPriceInteractor(presenter);
                FilterByPriceController controller = new FilterByPriceController(interactor);
                FilterByPriceInputData inputData = new FilterByPriceInputData(address, currentTime);
                ;

                // Execute the interactor
                try {
                    controller.handlePriceFiltering(address, currentTime);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }
}
