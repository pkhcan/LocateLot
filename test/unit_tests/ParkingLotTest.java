package unit_tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.ArrayList;

import entity.ParkingLot;

public class ParkingLotTest {

    private ParkingLot parkingLot;

    @Before
    public void setUp() {
        String id = "1";
        String streetAddress = "123 Main St";
        String linkToWebsite = "http://example.com";
        float[] latitudeLongitude = {40.7128f, -74.0060f};
        HashMap<String, String> timesToRates = new HashMap<>();
        timesToRates.put("9AM-5PM", "$10");
        timesToRates.put("5PM-12AM", "$15");

        parkingLot = new ParkingLot(id, streetAddress, linkToWebsite, latitudeLongitude, timesToRates);
    }
    @Test
    public void testGetAddress() {
        assertEquals("123 Main St", parkingLot.getAddress());
    }

    @Test
    public void testGetWebsiteLink() {
        assertEquals("http://example.com", parkingLot.getWebsiteLink());
    }

    @Test
    public void testGetLatitudeLongitude() {
        float[] expected = {40.7128f, -74.0060f};
        assertArrayEquals(expected, parkingLot.getLatitudeLongitude(), 0);
    }

    @Test
    public void testGetRates() {
        HashMap<String, String> expected = new HashMap<>();
        expected.put("9AM-5PM", "$10");
        expected.put("5PM-12AM", "$15");
        assertEquals(expected, parkingLot.getRates());
    }

    @Test
    public void testGetEntryReview() {
        parkingLot.easeOfEntryReviews = new ArrayList<>();
        parkingLot.easeOfEntryReviews.add(5);
        parkingLot.easeOfEntryReviews.add(4);
        assertEquals("4.5", parkingLot.getEntryReview());
    }

    @Test
    public void testGetFindingReview() {
        parkingLot.easeOfFindingReviews = new ArrayList<>();
        parkingLot.easeOfFindingReviews.add(3);
        parkingLot.easeOfFindingReviews.add(2);
        assertEquals("2.5", parkingLot.getFindingReview());
    }

    @Test
    public void testGetEntryReview_NoReviews() {
        parkingLot.easeOfEntryReviews = new ArrayList<>();
        assertEquals("No reviews yet", parkingLot.getEntryReview());
    }

    @Test
    public void testGetFindingReview_NoReviews() {
        parkingLot.easeOfFindingReviews = new ArrayList<>();
        assertEquals("No reviews yet", parkingLot.getFindingReview());
    }
}

