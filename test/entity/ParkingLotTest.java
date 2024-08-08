package entity;

import data_access.ParkingLotDAO;
import entity.ParkingLot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Nested
class ParkingLotTest {

    private List<ParkingLot> parkingLots;
    private ParkingLotDAO parkingLotDAO;


    @BeforeEach
    public void priceFilterTestSetUp() throws IOException {
        parkingLots = new ArrayList<>();
        parkingLotDAO = new ParkingLotDAO();
    }


    @Test
    void testGetParkingLotPriceMorning() {
        parkingLots.add(parkingLotDAO.getParkingLots().get(0)); // 20 Charles Street East
        parkingLots.add(parkingLotDAO.getParkingLots().get(1)); // 13 Isabella Street
        parkingLots.add(parkingLotDAO.getParkingLots().get(2)); // 15 Wellesley Street
        parkingLots.add(parkingLotDAO.getParkingLots().get(3)); // 21 Pleasant Blvd
        assert (parkingLots.get(0).getPrice(parkingLots.get(0), 14).equals("$14.00"));
    }

    @Test
    void testGetParkingLotPriceNight() {
        parkingLots.add(parkingLotDAO.getParkingLots().get(0));
        assert (parkingLots.get(0).getPrice(parkingLots.get(0), 20).equals("$6.00"));

    }

    @Test
    void testGetParkingLotPriceNoPrice(){
        parkingLots.add(parkingLotDAO.getParkingLots().get(1));
        assert (parkingLots.get(0).getPrice(parkingLots.get(0), 14).equals("$3.00"));
    }

    @Test
    void testGetParkingLotPriceNoTimesToRates(){
        parkingLots.add(parkingLotDAO.getParkingLots().get(18));
        assert parkingLots.get(0).getPrice(parkingLots.get(0), 14).equals("$1.00");
    }

}
