package interface_adapter;


import app.gui.GUI;

import entity.ParkingLot;

import use_case.FilterOutput.OutputBoundary;
import use_case.FilterOutput.OutputData;

/**
 * Presenter class that presents Ease of Entry (EOE) output data to a GUI.
 */
public class EOEPresenter implements OutputBoundary {

    private final GUI gui;

    /**
     * Constructs an {@code EOEPresenter} with the specified GUI.
     *
     * @param gui the GUI to which EOE output data will be presented
     */
    public EOEPresenter(GUI gui) {
        this.gui = gui;
    }

    /**
     * Presents the EOE output data to the GUI by updating the parking lot list.
     *
     * @param outputData the output data containing sorted parking lots to present
     */
    @Override
    public void present(OutputData outputData) {
        ParkingLot[] sortedParkingLots = outputData.getSortedParkingLots();
        gui.updateParkingLotList(sortedParkingLots);

    }


    @Override
    public void presentError(String error) {
        gui.showError(error);
    }
}
