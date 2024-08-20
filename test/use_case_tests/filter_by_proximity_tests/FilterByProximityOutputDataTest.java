package use_case_tests.filter_by_proximity_tests;

import data_access.ParkingLotDAO;
import entity.ParkingLot;
import org.junit.jupiter.api.Test;
import use_case.FilterByProximity.FilterByProximityOutputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test cases for filter by proximity output data
 */

public class FilterByProximityOutputDataTest {

    /**
     * Test that filterByProximityOutputData can initialize and retrieve inputted list of parking lots
     * @throws IOException
     */
    @Test
    void testFilterByProximityOutputDataInitializationAndRetrieval() throws IOException {
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        List<ParkingLot> parkingLotDAOGetParkingLots = parkingLotDAO.getParkingLots();
        List<ParkingLot> expectedParkingLots = new ArrayList<>();
        expectedParkingLots.add(parkingLotDAOGetParkingLots.get(0));
        expectedParkingLots.add(parkingLotDAOGetParkingLots.get(1));

        FilterByProximityOutputData filterByProximityOutputData = new FilterByProximityOutputData(expectedParkingLots);
        assertNotNull(filterByProximityOutputData.getFilteredByProximity(), "OutputData should contain parking lot list");
        assertEquals(expectedParkingLots, filterByProximityOutputData.getFilteredByProximity());
    }
}
