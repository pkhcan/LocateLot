package unit_tests;

import data_access.ParkingLotDAO;
import entity.ParkingLot;
import entity.PriceFilter;
import entity.ProximityFilter;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Test cases for price filter
 */

public class PriceFilterTest {

    private PriceFilter priceFilter;
    private List<ParkingLot> parkingLots;
    ParkingLotDAO parkingLotDAO;

    /**
     * Sets up mock data for radius filter tests
     *
     * @throws IOException
     */
    @Before
    public void priceFilterTestSetUp() throws IOException {
        priceFilter = new PriceFilter();
        parkingLots = new ArrayList<>();
        parkingLotDAO = new ParkingLotDAO();

    }

    /**
     * Tests that the filter will return accurate results for radii under 1 km
     */
    @org.junit.Test
    public void testTwoLotsPriceFilterMorningCase() throws IOException {

        parkingLots.add(parkingLotDAO.getParkingLots().get(0)); // 20 Charles Street East
        parkingLots.add(parkingLotDAO.getParkingLots().get(2)); // 13 Isabella Street

        List<ParkingLot> sortedLots = priceFilter.sort(parkingLots, 14);
        assert (sortedLots.size() == 2);
        assert (sortedLots.get(0).getPrice(sortedLots.get(0), 14).equals("$14.00"));
        assert (sortedLots.get(1).getPrice(sortedLots.get(1), 14).equals("$20.00"));

    }

    @org.junit.Test
    public void testNoMaximumPriceMorningFilterCase() throws IOException {
        parkingLots.add(parkingLotDAO.getParkingLots().get(0)); // 20 Charles Street East
        parkingLots.add(parkingLotDAO.getParkingLots().get(1)); // 13 Isabella Street

        List<ParkingLot> sortedLots = priceFilter.sort(parkingLots, 14);
        assert (sortedLots.size() == 2);
        assert (sortedLots.get(0).getPrice(sortedLots.get(0), 14).equals("$3.00"));
        assert (sortedLots.get(1).getPrice(sortedLots.get(1), 14).equals("$14.00"));
    }

    @org.junit.Test
    public void testMultipleLotsEvening() throws IOException {
        parkingLots.add(parkingLotDAO.getParkingLots().get(0)); // 20 Charles Street East
        parkingLots.add(parkingLotDAO.getParkingLots().get(1)); // 13 Isabella Street
        parkingLots.add(parkingLotDAO.getParkingLots().get(2)); // 13 Isabella Street


        List<ParkingLot> sortedLots = priceFilter.sort(parkingLots, 18);
        assert (sortedLots.size() == 3);
        assert (sortedLots.get(0).getPrice(sortedLots.get(0), 18).equals("$6.00"));
        assert (sortedLots.get(1).getPrice(sortedLots.get(1), 18).equals("$9.00"));
        assert (sortedLots.get(2).getPrice(sortedLots.get(2), 18).equals("$10.00"));

    }

}
