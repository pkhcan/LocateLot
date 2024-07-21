package use_case_tests;

import interface_adapter.EOEPresenter;
import use_case_tests.FakeGUI;
import org.junit.jupiter.api.Test;
import use_case.FilterOutput.OutputData;
import entity.ParkingLot;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for interface_adapter.EOEPresenter.
 */
class EOEPresenterTest {

    /**
     * Tests the EOEPresenter.present(OutputData) method.
     * Ensures that the parking lots passed to the GUI match the expected lots.
     */
    @Test
    void testPresent() {
        // setUp
        FakeGUI fakeGUI = new FakeGUI();
        EOEPresenter presenter = new EOEPresenter(fakeGUI);
        ParkingLot[] expectedLots = new ParkingLot[]{
                new ParkingLot("1", "123 Main St", "http://pl1.com", new double[]{40.7128f, -74.0060f}, "Garage", "10.00", null, 50),
                new ParkingLot("2", "456 Main St", "http://pl2.com", new double[]{40.7130f, -74.0070f}, "Surface", "12.00", null, 75)
        };
        OutputData outputData = new OutputData(expectedLots);

        // present
        presenter.present(outputData);

        // check
        assertArrayEquals(expectedLots, fakeGUI.displayedParkingLots, "The parking lots passed to the GUI should match the expected lots.");
    }

    /**
     * Tests the EOEPresenter.presentError(String) method.
     * Ensures that the error message is printed to System.out.
     */
    @Test
    void testPresentError() {
        // setUp
        FakeGUI fakeGUI = new FakeGUI();
        EOEPresenter presenter = new EOEPresenter(fakeGUI);
        String expectedError = "An error occurred";

        // capture System.out
        final java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // present
        presenter.presentError(expectedError);

        // check
        assertEquals(expectedError + System.lineSeparator(), outContent.toString(), "The error message should be printed to System.out.");

        // reset
        System.setOut(System.out);
    }
}
