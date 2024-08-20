package use_case_tests.filter_by_price_tests;

import data_access.ParkingLotDAO;
import entity.ParkingLot;
import org.junit.jupiter.api.Test;
import use_case.FilterByPrice.FilterByPriceOutputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Filter by price output data test cases
 */

class FilterByPriceOutputDataTest {

    /**
     * Tests that filter by radius output data can initialize and retrieve list of parking lots.
     * @throws IOException
     */
    @Test
    void testFilterByPriceOutputDataInitializationAndRetrieval() throws IOException {
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        List<ParkingLot> parkingLotDAOGetParkingLots = parkingLotDAO.getParkingLots();
        List<ParkingLot> expectedParkingLots = new ArrayList<>();
        expectedParkingLots.add(parkingLotDAOGetParkingLots.get(0));
        expectedParkingLots.add(parkingLotDAOGetParkingLots.get(1));

        FilterByPriceOutputData filterByPriceOutputData = new FilterByPriceOutputData(expectedParkingLots);
        assertNotNull(filterByPriceOutputData.getSortedParkingLots(), "OutputData should contain parking lot list");
        assertEquals(expectedParkingLots, filterByPriceOutputData.getSortedParkingLots());
    }

}