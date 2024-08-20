package unit_tests.interface_adapters;

import data_access.ParkingLotDAO;
import entity.ParkingLot;
import interface_adapter.FilterByRadiusPresenter;
import interface_adapter.FilterByRadiusViewModel;
import org.junit.jupiter.api.Test;
import use_case.FilterByRadius.FilterByRadiusOutputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Filter by radius presenter test cases
 * @throws IOException
 */
public class FilterByRadiusPresenterTest {
    private FilterByRadiusPresenter presenter;

    /**
     * Test that filter by radius can prepare a success view for the GUI.
     * @throws IOException
     */
    @Test
    public void FilterByRadiusPresenterSuccessViewTest() throws IOException {

        FilterByRadiusViewModel filterByRadiusViewModel = new FilterByRadiusViewModel();
        presenter = new FilterByRadiusPresenter(filterByRadiusViewModel);
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        List<ParkingLot> parkingLot = new ArrayList<>();
        parkingLot.add(parkingLotDAO.getParkingLots().get(0));
        parkingLot.add(parkingLotDAO.getParkingLots().get(1));
        FilterByRadiusOutputData outputData = new FilterByRadiusOutputData(parkingLot);
        presenter.prepareSuccessView(outputData);

        assertEquals(parkingLot, filterByRadiusViewModel.getParkingLots());

    }

    /**
     * test that filter by radius can create a fail view and correctly output a given message
     */
    @Test
    public void FilterByRadiusPresenterFailViewTest() {

        FilterByRadiusViewModel filterByRadiusViewModel = new FilterByRadiusViewModel();
        FilterByRadiusPresenter presenter = new FilterByRadiusPresenter(filterByRadiusViewModel);
        String expectedError = "An error occurred";

        // Capture System.out
        final java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        presenter.prepareFailView(expectedError);

        assertEquals(expectedError, filterByRadiusViewModel.getErrorMessage());
    }
}
