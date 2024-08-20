package use_case_tests.filter_by_price_tests;

import entity.ParkingLot;
import interface_adapter.FilterByPricePresenter;
import org.junit.jupiter.api.Test;
import use_case.FilterByPrice.FilterByPriceOutputData;
import use_case_tests.FakeGUI;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test class for the Filter By Price presenter
 */
class FilterByPricePresenterTest {


    /**
     * Tests the FilterByPricePresenter.prepareSuccessView(FilterByPriceOutputData) method.
     * Ensures that the parking lots passed to the GUI match the expected lots.
     */
    @Test
    void testPrepareSuccessView() {
        // setUp
        FakeGUI fakeGUI = new FakeGUI();
        FilterByPricePresenter presenter = new FilterByPricePresenter(fakeGUI);
        List<ParkingLot> expectedLots = new ArrayList<>();
        expectedLots.add(new ParkingLot("1", "123 Main St", "http://pl1.com", new double[]{40.7128f, -74.0060f}, "Garage", "10.00", null, 50));
        expectedLots.add(new ParkingLot("2", "456 Main St", "http://pl2.com", new double[]{40.7130f, -74.0070f}, "Surface", "12.00", null, 75));
        FilterByPriceOutputData outputData = new FilterByPriceOutputData(expectedLots);

        // present
        presenter.prepareSuccessView(outputData);

        // check
        assertEquals(expectedLots, fakeGUI.displayedParkingLotsList, "The parking lots passed to the GUI should match the expected lots.");
    }


    /**
     * Tests the FilterByPricePresenter.prepareFailView(String) method.
     * Ensures that the error message is printed to System.out.
     */
    @Test
    void prepareFailView() {
        FakeGUI fakeGUI = new FakeGUI();
        FilterByPricePresenter presenter = new FilterByPricePresenter(fakeGUI);
        String expectedError = "An error occurred";

        // present
        presenter.prepareFailView(expectedError);

        // check
        assertEquals(expectedError, fakeGUI.errorMessage, "The error message should be captured by the FakeGUI.");

    }
}