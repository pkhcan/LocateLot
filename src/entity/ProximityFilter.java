package entity;

import com.google.maps.model.GeocodingResult;
import data_access.GeoApiDAO;

import java.util.ArrayList;
import java.util.List;

public class ProximityFilter {
    public ProximityFilter() {
    }

    public List<ParkingLot> filter(double latitude, double longitude, ArrayList<ParkingLot> parkingLots) {
        List<ParkingLot> filteredByProximity = new ArrayList<>();
        while (!parkingLots.isEmpty()) {
            ParkingLot closest = getClosestParkingLot(latitude, longitude, parkingLots); // can't implement due to having to access DAO
            parkingLots.remove(closest);
            filteredByProximity.add(closest);
        }

        return filteredByProximity;
    }

    private static ParkingLot getClosestParkingLot(double latitude, double longitude, ArrayList<ParkingLot> parkingLots) {
        if (parkingLots == null || parkingLots.isEmpty()) return null;
        ParkingLot closest = null;
        double smallestDistance = Double.MAX_VALUE;

        for (ParkingLot parkingLot : parkingLots) {
            double[] latLong = parkingLot.getLatitudeLongitude();
            double distance = Math.hypot(latitude - latLong[0], longitude - latLong[1]);

            if (distance < smallestDistance) {
                smallestDistance = distance;
                closest = parkingLot;
            }
        }

        return closest;
    }
}
