//package unit_tests;
//
//import data_access.ParkingLotDAO;
//import entity.ParkingLot;
//import entity.TypeFilter;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Test class for TypeFilter entity
// */
//public class TypeFilterTest {
//    private TypeFilter typeFilter;
//    private List<ParkingLot> parkingLots;
//
//    /**
//     * Sets up mock data for type filter tests
//     */
//    @Before
//    public void typeFilterTestSetUp() throws IOException {
//        typeFilter = new TypeFilter();
//        parkingLots = new ArrayList<>();
//
//        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
//
//        parkingLots.add(parkingLotDAO.getParkingLots().get(0)); // 20 Charles Street East (garage)
//        parkingLots.add(parkingLotDAO.getParkingLots().get(1)); // 13 Isabella Street (surface)
//        parkingLots.add(parkingLotDAO.getParkingLots().get(2)); // 15 Wellesley Street (surface)
//        parkingLots.add(parkingLotDAO.getParkingLots().get(3)); // 21 Pleasant Blvd (garage)
//    }
//
//    /**
//     * Tests that the filter will return only surface parking lots
//     */
//    @Test
//    public void testTypeFilterSurface() {
//        List<ParkingLot> filteredLots = typeFilter.filter("surface", new ArrayList<>(parkingLots));
//
//        assertEquals(2, filteredLots.size());
//        assertEquals("13 Isabella Street", filteredLots.get(0).getAddress());
//        assertEquals("15 Wellesley Street", filteredLots.get(1).getAddress());
//    }
//
//    /**
//     * Tests that the filter will return only garage parking lots
//     */
//    @Test
//    public void testTypeFilterGarage() {
//        List<ParkingLot> filteredLots = typeFilter.filter("garage", new ArrayList<>(parkingLots));
//
//        assertEquals(2, filteredLots.size());
//        assertEquals("20 Charles Street East", filteredLots.get(0).getAddress());
//        assertEquals("21 Pleasant Blvd", filteredLots.get(1).getAddress());
//    }
//
//    /**
//     * Tests that the filter will return an empty list if no parking lots match the type
//     */
//    @Test
//    public void testTypeFilterNoMatch() {
//        List<ParkingLot> filteredLots = typeFilter.filter("invalidType", new ArrayList<>(parkingLots));
//
//        assertEquals(0, filteredLots.size());
//    }
//
//    /**
//     * Tests that the filter will return all parking lots if the type is not specified
//     */
//    @Test
//    public void testTypeFilterAll() {
//        List<ParkingLot> filteredLots = typeFilter.filter("", new ArrayList<>(parkingLots));
//
//        assertEquals(4, filteredLots.size());
//    }
//}
