package unit_tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import entity.ParkingLot;
import entity.EOEFilter;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Unit tests for the EOEFilter class.
 */
public class EOEFilterTest {
    private EOEFilter eoeFilter;

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        eoeFilter = new EOEFilter();
    }

    /**
     * Tests filtering of parking lots with entry reviews.
     * Ensures that parking lots are sorted based on their reviews.
     */
    @Test
    public void testFilterWithReviews() {
        ParkingLot pl1 = new ParkingLot("1", "123 Lot St", "http://pl1.com", new float[]{40.7128f, -74.0060f}, new HashMap<>());
        ParkingLot pl2 = new ParkingLot("2", "456 Lot St", "http://pl2.com", new float[]{40.7129f, -74.0070f}, new HashMap<>());
        ParkingLot pl3 = new ParkingLot("3", "789 Lot St", "http://pl3.com", new float[]{40.7130f, -74.0080f}, new HashMap<>());

        pl1.easeOfEntryReviews.add(5);
        pl1.easeOfEntryReviews.add(4);
        pl2.easeOfEntryReviews.add(3);
        pl2.easeOfEntryReviews.add(2);
        pl3.easeOfEntryReviews.add(1);

        ParkingLot[] parkingLots = {pl1, pl2, pl3};
        eoeFilter.filter(parkingLots);

        assertEquals(pl1, parkingLots[0]);
        assertEquals(pl2, parkingLots[1]);
        assertEquals(pl3, parkingLots[2]);

    }

    /**
     * Tests filtering of parking lots where some lots are unrated.
     * Ensures that unrated lots are placed at the end.
     */
    @Test
    public void testFilterWithUnratedLots() {
        ParkingLot pl1 = new ParkingLot("1", "123 Main St", "http://example1.com", new float[]{40.7128f, -74.0060f}, new HashMap<>());
        ParkingLot pl2 = new ParkingLot("2", "456 Main St", "http://example2.com", new float[]{40.7129f, -74.0070f}, new HashMap<>());
        ParkingLot pl3 = new ParkingLot("3", "789 Main St", "http://example3.com", new float[]{40.7130f, -74.0080f}, new HashMap<>());

        pl1.easeOfEntryReviews.add(5);
        pl1.easeOfEntryReviews.add(4);
//      Unrated lot pl2
        pl3.easeOfEntryReviews.add(3);
        pl3.easeOfEntryReviews.add(2);

        ParkingLot[] parkingLots = {pl1, pl2, pl3};
        eoeFilter.filter(parkingLots);

        assertEquals(pl1, parkingLots[0]);
        assertEquals(pl3, parkingLots[1]);
        assertEquals(pl2, parkingLots[2]);
    }

    /**
     * Tests filtering when all parking lots are unrated.
     * Ensures that the order of lots remains unchanged.
     */
    @Test
    public void testFilterAllUnratedLots() {
        ParkingLot pl1 = new ParkingLot("1", "123 Main St", "http://example1.com", new float[]{40.7128f, -74.0060f}, new HashMap<>());
        ParkingLot pl2 = new ParkingLot("2", "456 Main St", "http://example2.com", new float[]{40.7129f, -74.0070f}, new HashMap<>());
        ParkingLot pl3 = new ParkingLot("3", "789 Main St", "http://example3.com", new float[]{40.7130f, -74.0080f}, new HashMap<>());

        pl1.easeOfEntryReviews = new ArrayList<>(); // Unrated lot
        pl2.easeOfEntryReviews = new ArrayList<>(); // Unrated lot
        pl3.easeOfEntryReviews = new ArrayList<>(); // Unrated lot

        ParkingLot[] parkingLots = {pl1, pl2, pl3};
        eoeFilter.filter(parkingLots);

        assertEquals(pl1, parkingLots[0]);
        assertEquals(pl2, parkingLots[1]);
        assertEquals(pl3, parkingLots[2]);
    }
}
