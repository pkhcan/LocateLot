package entity;

import java.util.ArrayList;
import java.util.List;

public class ProximityFilter {

    public ProximityFilter() {
    }

    /**
     *
     * @param latitude current latitude
     * @param longitude current longitude
     * @param parkingLots list of parking lots to be filtered
     * @return List of parking lots filtered by proximity
     */
    public List<ParkingLot> filter(double latitude, double longitude, ArrayList<ParkingLot> parkingLots) {
        List<ParkingLot> filteredByProximity = new ArrayList<>();
        /*
        iterates through a mutable arraylist of parking lots and removes the closest one each time to add
        to new filtered list. A parking lot is always removed so that the getClosestParkingLot method
        can get the next closest parking lot in the list.
         */
        while (!parkingLots.isEmpty()) {
            ParkingLot closest = getClosestParkingLot(latitude, longitude, parkingLots);
            parkingLots.remove(closest);
            filteredByProximity.add(closest);
        }

        return filteredByProximity;
    }

    /**
     * Iterates through a list of parking lots to find the parking lot closest to a given set of
     * coordinate points.
     * @param latitude current latitude from user input for address
     * @param longitude current longitude from user input for address
     * @param parkingLots list of parking lots to be searched
     * @return closest parking lot in the list
     */
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
