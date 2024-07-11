package data_access;

import entity.ParkingLot;

import java.util.HashMap;

import java.util.List;

public class ParkingLotFactory {

    public static ParkingLot createParkingLot(String id, String website, List<String> latLong, String address, HashMap<String, String> timesToRates) {

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(id);
        parkingLot.setWebsite(website);
        parkingLot.setLatLong(latLong);
        parkingLot.setAddress(address);
        parkingLot.setTimesToRates(timesToRates);

        return parkingLot;
    }
}
