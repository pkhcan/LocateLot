package data_access;

import entity.ParkingLot;

import java.util.HashMap;

import java.util.List;

public class ParkingLotFactory {

    public static ParkingLot createParkingLot(String id, String website, String carparkType, float[] latLong, String streetAddress, HashMap<String, String> timesToRates) {

        ParkingLot parkingLot = new ParkingLot(streetAddress);
        parkingLot.setId(id);
        parkingLot.setWebsite(website);
        parkingLot.setLatLong(latLong);
        parkingLot.setAddress(streetAddress);
        parkingLot.setTimestoRates(timesToRates);
        parkingLot.setCarparkType(carparkType);

        return parkingLot;
    }
}
