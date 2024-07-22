package use_case_tests;

import data_access.ParkingLotDAO;
import entity.ParkingLot;
import org.junit.jupiter.api.Test;
import use_case.FilterByRadius.FilterByRadiusOutputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Filter by radius output data test cases
 */

public class FilterByRadiusOutputDataTest {

    /**
     * Tests that filter by radius output data can initialize and retrieve list of parking lots.
     * @throws IOException
     */
    @Test
    void testFilterByRadiusOutputDataInitializationAndRetrieval() throws IOException {
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        List<ParkingLot> parkingLotDAOGetParkingLots = parkingLotDAO.getParkingLots();
        List<ParkingLot> expectedParkingLots = new ArrayList<>();
        expectedParkingLots.add(parkingLotDAOGetParkingLots.get(0));
        expectedParkingLots.add(parkingLotDAOGetParkingLots.get(1));

        FilterByRadiusOutputData filterByRadiusOutputData = new FilterByRadiusOutputData(expectedParkingLots);
        assertNotNull(filterByRadiusOutputData.getParkingLots(), "OutputData should contain parking lot array");
        assertEquals(expectedParkingLots, filterByRadiusOutputData.getParkingLots());
    }
}
