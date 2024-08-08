package unit_tests;

import static org.junit.Assert.*;

import data_access.ParkingLotDAO;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import entity.ParkingLot;

public class ParkingLotTest {

    private ParkingLot parkingLot;
    private List<ParkingLot> parkingLots;
    private ParkingLotDAO parkingLotDAO;

    @Before
    public void setUp() throws IOException {
        String id = "1";
        String streetAddress = "123 Main St";
        String carParkType = "Garage";
        String linkToWebsite = "http://example.com";
        double[] latitudeLongitude = {40.7128f, -74.0060f};
        String halfHourlyRate = "7.50";
        HashMap<String, String> timesToRates = new HashMap<>();
        timesToRates.put("9AM-5PM", "$10");
        timesToRates.put("5PM-12AM", "$15");
        int capacity = 3;
        parkingLotDAO = new ParkingLotDAO();
        parkingLots = new ArrayList<>();

        parkingLot = new ParkingLot(id, streetAddress, linkToWebsite, latitudeLongitude, carParkType, halfHourlyRate, timesToRates, capacity);
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
        double[] expected = {40.7128f, -74.0060f};
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

    @Test
    public void testSetCarParkType () {
        parkingLot.setCarparkType("Surface");
        assertEquals("Surface", parkingLot.getCarParkType());
    }

    @Test
    public void testGetParkingLotPriceMorning(){
        parkingLots.add(parkingLotDAO.getParkingLots().get(0));
        parkingLots.add(parkingLotDAO.getParkingLots().get(1));
        parkingLots.add(parkingLotDAO.getParkingLots().get(2));
        parkingLots.add(parkingLotDAO.getParkingLots().get(3));
        assert (parkingLots.getFirst().getPrice(parkingLots.getFirst(), 14).equals("$14.00"));

    }
    @Test
    public void testGetParkingLotPriceNight() {
        parkingLots.add(parkingLotDAO.getParkingLots().getFirst());
        assert (parkingLots.getFirst().getPrice(parkingLots.getFirst(), 20).equals("$6.00"));

    }

    @Test
    public void testGetParkingLotPriceNoPrice(){
        parkingLots.add(parkingLotDAO.getParkingLots().get(1));
        assert (parkingLots.getFirst().getPrice(parkingLots.getFirst(), 14).equals("$3.00"));
    }

    @Test
    public void testGetParkingLotPriceNoTimesToRates(){
        parkingLots.add(parkingLotDAO.getParkingLots().get(18));
        assert parkingLots.getFirst().getPrice(parkingLots.getFirst(), 14).equals("$1.00");
    }

}

