package use_case_tests;

import entity.ParkingLot;
import use_case.FilterOutput.OutputData;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for use_case.FilterOutput.OutputData.
 */
public class OutputDataTest {

    /**
     * Tests the initialization and retrieval of parking lots from FilterOutput.OutputData.
     * Ensures that the parking lots are correctly stored and retrieved.
     */
    @Test
    void testOutputDataInitializationAndRetrieval() {
        // setUp - construct parking lot objects
        ParkingLot pl1 = new ParkingLot("1", "123 Main St", "http://example1.com", new double[]{40.7128, -74.0060}, "Garage", "10.00", new HashMap<>(), 1);
        ParkingLot pl2 = new ParkingLot("2", "456 Main St", "http://example2.com", new double[]{40.7129, -74.0070}, "Surface", "12.00", new HashMap<>(), 2);

        // expected array of parking lots to be stored in OutputData
        ParkingLot[] expectedParkingLots = new ParkingLot[] {pl1, pl2};

        OutputData outputData = new OutputData(expectedParkingLots);

        // check
        assertNotNull(outputData.getSortedParkingLots(), "OutputData should contain parking lot array");
        assertArrayEquals(expectedParkingLots, outputData.getSortedParkingLots(), "The parking lots returned were not as expected");
    }
}
