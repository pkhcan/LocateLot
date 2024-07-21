package unit_tests;

import entity.EOFFilter;
import entity.ParkingLot;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;


/**
 * Unit tests for the EOFFilter class.
 */
public class EOFFilterTest {
    private EOFFilter eofFilter;

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        eofFilter = new EOFFilter();
    }

    /**
     * Tests filtering of parking lots based on capacity.
     * Ensures that parking lots are sorted based on their capacity.
     */
    @Test
    public void testFilterWithCapacities() {
        ParkingLot pl1 = new ParkingLot("1", "123 Lot St", "http://pl1.com", new double[]{40.7128f, -74.0060f},"Type1",  "10.00", new HashMap<>(), 1);
        ParkingLot pl2 = new ParkingLot("2", "456 Lot St", "http://pl2.com", new double[]{40.7129f, -74.0070f}, "Type2", "12.00", new HashMap<>(), 2);
        ParkingLot pl3 = new ParkingLot("3", "789 Lot St", "http://pl3.com", new double[]{40.7130f, -74.0080f}, "Type3", "15.00", new HashMap<>(), 3);

        ParkingLot[] parkingLots = {pl1, pl2, pl3};
        eofFilter.filter(parkingLots);

        assertEquals(pl1, parkingLots[2]);
        assertEquals(pl2, parkingLots[1]);
        assertEquals(pl3, parkingLots[0]);

    }




}
