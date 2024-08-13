package use_case_tests.filter_by_radius_tests;

import data_access.ParkingLotDAO;
import entity.ParkingLot;
import interface_adapter.FilterByRadiusPresenter;
import interface_adapter.FilterByRadiusViewModel;
import org.junit.jupiter.api.Test;
import use_case.FilterByRadius.FilterByRadiusOutputData;
import use_case_tests.FakeGUI;

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
        // TODO: DELETE
        // FakeGUI fakeGUI = new FakeGUI();
        FilterByRadiusViewModel filterByRadiusViewModel = new FilterByRadiusViewModel();
        presenter = new FilterByRadiusPresenter(filterByRadiusViewModel);
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        List<ParkingLot> parkingLot = new ArrayList<>();
        parkingLot.add(parkingLotDAO.getParkingLots().get(0));
        parkingLot.add(parkingLotDAO.getParkingLots().get(1));
        FilterByRadiusOutputData outputData = new FilterByRadiusOutputData(parkingLot);
        presenter.prepareSuccessView(outputData);

        // TODO: DELETE
        // assertEquals(parkingLot, fakeGUI.displayedParkingLotsList);
        assertEquals(parkingLot, filterByRadiusViewModel.getParkingLots());

    }

    /**
     * test that filter by radius can create a fail view and correctly output a given message
     */
    @Test
    public void FilterByRadiusPresenterFailViewTest() {
        // TODO: DELETE
        // FakeGUI fakeGUI = new FakeGUI();
        FilterByRadiusViewModel filterByRadiusViewModel = new FilterByRadiusViewModel();
        FilterByRadiusPresenter presenter = new FilterByRadiusPresenter(filterByRadiusViewModel);
        String expectedError = "An error occurred";

        // Capture System.out
        final java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        presenter.prepareFailView(expectedError);

        // TODO: DELETE
        // assertEquals(expectedError + System.lineSeparator(), outContent.toString());
        assertEquals(expectedError, filterByRadiusViewModel.getErrorMessage());
    }
}
