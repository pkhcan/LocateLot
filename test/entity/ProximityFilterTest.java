package entity;

import data_access.ParkingLotDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProximityFilterTest {

    ProximityFilter proximityFilter;
    ParkingLotDAO parkingLotDAO;
    List<ParkingLot> parkingLots;

    /**
     * Sets up mock data for radius filter tests
     * @throws IOException
     */
    @BeforeEach
    void setUp() throws IOException {
        proximityFilter = new ProximityFilter();
        parkingLots = new ArrayList<>();
        // Add some parking lots with known coordinates
        parkingLotDAO = new ParkingLotDAO();
    }

    /**
     * Tests that the filter will return accurate results for radii under 1 km
     */
    @Test
    void filter() {
        parkingLots.add(parkingLotDAO.getParkingLots().get(0)); // 20 Charles Street East
        parkingLots.add(parkingLotDAO.getParkingLots().get(1)); // 13 Isabella Street
        parkingLots.add(parkingLotDAO.getParkingLots().get(2)); // 15 Wellesley Street
        parkingLots.add(parkingLotDAO.getParkingLots().get(3)); // 21 Pleasant Blvd
        List<ParkingLot> filteredLots = proximityFilter.filter(43.669282202140174,
                -79.3852894625656, (ArrayList) parkingLots); // 0.35 km radius from 20 Charles Street East

        assert(filteredLots.size() == 4);
        assert(filteredLots.get(0).getAddress().equals("20 Charles Street East"));
        assert(filteredLots.get(1).getAddress().equals("13 Isabella Street"));
        assert(filteredLots.get(2).getAddress().equals("15 Wellesley Street East"));
        assert(filteredLots.get(3).getAddress().equals("21 Pleasant Blvd."));

    }

    /**
     * Tests that proximity filter returns an empty list when given an empty list
     */
    @org.junit.Test
    public void testProximityFilterEmptyList() {
        List<ParkingLot> parkingLotsEmpty = new ArrayList<>();
        List<ParkingLot> filteredList = proximityFilter.filter(43.669282202140174, -79.3852894625656,
                (ArrayList) parkingLotsEmpty);

        assert(filteredList.isEmpty());

    }

    /**
     * Tests that proximity filter can work with a list with only one item and returns the appropriate
     * result
     */

    @org.junit.Test
    public void testProximityFilterOneItem() {
        List<ParkingLot> parkingLotsOneLot = new ArrayList<>();
        parkingLotsOneLot.add(parkingLotDAO.getParkingLots().get(0));
        List<ParkingLot> filteredList = proximityFilter.filter(43.669282202140174, -79.3852894625656,
                (ArrayList) parkingLotsOneLot);

        assert(filteredList.get(0).getAddress().equals("20 Charles Street East"));
        assert(filteredList.size() == 1);
    }

    /**
     * Tests the proximity filter can handle cases with duplicate parking lot objects
     */
    @org.junit.Test
    public void testProximityFilterDuplicateLots() {
        List<ParkingLot> parkingLotsDuplicateLots = new ArrayList<>();
        parkingLotsDuplicateLots.add(parkingLotDAO.getParkingLots().get(0));
        parkingLotsDuplicateLots.add(parkingLotDAO.getParkingLots().get(0));

        parkingLotsDuplicateLots.add(parkingLotDAO.getParkingLots().get(1));

        List<ParkingLot> filteredList = proximityFilter.filter(43.669282202140174, -79.3852894625656,
                (ArrayList) parkingLotsDuplicateLots);

        assert(filteredList.size() == 3);
    }
}