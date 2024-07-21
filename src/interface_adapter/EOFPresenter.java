package interface_adapter;


import app.gui.GUI;
import entity.ParkingLot;
import use_case.FilterOutput.OutputBoundary;
import use_case.FilterOutput.OutputData;

/**
 * Presenter class that presents Ease of Finding (EOF) output data to a GUI.
 */
public class EOFPresenter implements OutputBoundary {

    private final GUI gui;

    /**
     * Constructs an {@code EOFPresenter} with the specified GUI.
     *
     * @param gui the GUI to which EOF output data will be presented
     */
    public EOFPresenter(GUI gui) {
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
}
