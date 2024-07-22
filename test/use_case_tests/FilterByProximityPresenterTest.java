package use_case_tests;

import data_access.ParkingLotDAO;
import entity.ParkingLot;
import interface_adapter.FilterByProximityPresenter;
import interface_adapter.FilterByRadiusPresenter;
import org.junit.jupiter.api.Test;
import use_case.FilterByProximity.FilterByProximityOutputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for FilterByProximityPresenter
 */

public class FilterByProximityPresenterTest {
    private FilterByProximityPresenter presenter;

    /**
     * Test that FilterByProximityPresenter.prepareSuccessView passes expected output to GUI.
     * @throws IOException
     */
    @Test
    public void FilterByProximityPresenterSuccessViewTest() throws IOException {
        FakeGUI fakeGUI = new FakeGUI();
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(parkingLotDAO.getParkingLots().get(0));
        parkingLots.add(parkingLotDAO.getParkingLots().get(1));
        FilterByProximityOutputData outputData = new FilterByProximityOutputData(parkingLots);
        presenter = new FilterByProximityPresenter(fakeGUI);
        presenter.prepareSuccessView(outputData);

        assertEquals(parkingLots, fakeGUI.displayedParkingLotsList);


    }


    /**
     * Test that FilterByProximityPresenter.prepareFailView displays an error message
     */
    @Test
    public void FilterByProximityPresenterFailViewTest() {
        FakeGUI fakeGUI = new FakeGUI();
        FilterByProximityPresenter presenter = new FilterByProximityPresenter(fakeGUI);
        String expectedError = "An error occurred";

        // Capture System.out
        final java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        presenter.prepareFailView(expectedError);

        assertEquals(expectedError + System.lineSeparator(), outContent.toString());
    }
}
