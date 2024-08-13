package entity;

import data_access.ParkingLotDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * RadiusFilter entity class.
 */
public class RadiusFilterTest {

    RadiusFilter radiusFilter;
    List<ParkingLot> parkingLots;

    /**
     * Sets up mock data for radius filter tests
     * @throws IOException
     */
    @BeforeEach
    void setUp() throws IOException {
        radiusFilter = new RadiusFilter();
        parkingLots = new ArrayList<>();
        // Add some parking lots with known coordinates
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        parkingLots.add(parkingLotDAO.getParkingLots().get(0)); // 20 Charles Street East
        parkingLots.add(parkingLotDAO.getParkingLots().get(1)); // 13 Isabella Street
        parkingLots.add(parkingLotDAO.getParkingLots().get(2)); // 15 Wellesley Street
        parkingLots.add(parkingLotDAO.getParkingLots().get(3)); // 21 Pleasant Blvd
    }

    /**
     * Tests that the filter will return accurate results for radii under 1 km
     */
    @Test
    void filter() {
        List<ParkingLot> filteredLots = radiusFilter.filter(0.350, 43.669282202140174,
                -79.3852894625656, parkingLots); // 0.35 km radius from 20 Charles Street East

        for (ParkingLot parkingLot : filteredLots) {
            System.out.println(parkingLot);
        }

        assert(filteredLots.size() == 2);
        assert(filteredLots.get(0).getAddress().equals("20 Charles Street East"));
        assert(filteredLots.get(1).getAddress().equals("13 Isabella Street"));
    }

    /**
     * Tests that the filter will default to 3 km without a specified radius.
     */
    @Test
    void testFilterDefault() {
        // default radius 3 km
        List<ParkingLot> filteredLots = radiusFilter.filter(43.669282202140174, -79.3852894625656, parkingLots);

        for (ParkingLot parkingLot : filteredLots) {
            System.out.println(parkingLot);
        }

        assert(filteredLots.size() == 4);
        assert(filteredLots.get(0).getAddress().equals("20 Charles Street East"));
        assert(filteredLots.get(1).getAddress().equals("13 Isabella Street"));
    }

    /**
     * Tests that an empty list is returned if a parking lot with the exact same coordinates does not exist in our
     * dataset
     */
    @org.junit.Test
    public void testRadiusFilterZeroRadius() {
        parkingLots.remove(0); // remove parking lot with exact coordinate points in test
        List<ParkingLot> filteredLots = radiusFilter.filter(0.0, 43.669282202140174,
                -79.3852894625656, parkingLots); // 0 km radius

        assert(filteredLots.isEmpty());
        parkingLots.addFirst(parkingLots.get(0)); // add parking lot back to parking lot list
    }

    /**
     * Tests that the radius filter returns all parking lots in the data set for a radius large enough to account for
     * all parking lots in the dataset
     */
    @org.junit.Test
    public void testRadiusFilterLargeRadius() {
        List<ParkingLot> filteredLots = radiusFilter.filter(1000.0, 43.669282202140174,
                -79.3852894625656, parkingLots); // 1000 km radius

        assert(filteredLots.size() == parkingLots.size());
    }

    /**
     * Tests that the radius filter will include parking lots with coordinates that are exactly a certain distance
     * away. 13 Isabella Street is exactly 0.35 km away from 20 Charles Street East.
     */
    @org.junit.Test
    public void testRadiusFilterExactBoundary() {
        List<ParkingLot> filteredLots = radiusFilter.filter(0.350, 43.669282202140174,
                -79.3852894625656, parkingLots);

        assert(filteredLots.size() == 2);
        assert(filteredLots.get(1).getAddress().equals("13 Isabella Street"));
    }

    /**
     * Test that the radius filter can handle an empty list by returning an empty list.
     */
    @org.junit.Test
    public void testRadiusFilterEmptyList() {
        List<ParkingLot> emptyParkingLots = new ArrayList<>();
        List<ParkingLot> filteredLots = radiusFilter.filter(1.0, 43.669282202140174,
                -79.3852894625656, emptyParkingLots);

        assert(filteredLots.isEmpty());
    }
}