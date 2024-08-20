package unit_tests.interface_adapters;

import data_access.ParkingLotDAO;
import entity.ParkingLot;
import interface_adapter.FilterByProximityPresenter;
import interface_adapter.FilterByProximityViewModel;
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
     * Test that filter by proximity can prepare a success view for the GUI.
     * @throws IOException
     */
    @Test
    public void FilterByProximityPresenterSuccessViewTest() throws IOException {

        FilterByProximityViewModel filterByProximityViewModel = new FilterByProximityViewModel();
        presenter = new FilterByProximityPresenter(filterByProximityViewModel);
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        List<ParkingLot> parkingLot = new ArrayList<>();
        parkingLot.add(parkingLotDAO.getParkingLots().get(0));
        parkingLot.add(parkingLotDAO.getParkingLots().get(1));
        FilterByProximityOutputData outputData = new FilterByProximityOutputData(parkingLot);
        presenter.prepareSuccessView(outputData);

        assertEquals(parkingLot, filterByProximityViewModel.getParkingLots());

    }

    /**
     * test that filter by Proximity can create a fail view and correctly output a given message
     */
    @Test
    public void FilterByProximityPresenterFailViewTest() {

        FilterByProximityViewModel filterByProximityViewModel = new FilterByProximityViewModel();
        FilterByProximityPresenter presenter = new FilterByProximityPresenter(filterByProximityViewModel);
        String expectedError = "An error occurred";

        // Capture System.out
        final java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        presenter.prepareFailView(expectedError);

        assertEquals(expectedError, filterByProximityViewModel.getErrorMessage());
    }
}
