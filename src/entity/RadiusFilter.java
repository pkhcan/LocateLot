package entity;

import java.util.ArrayList;
import java.util.List;

public class RadiusFilter {
    List<ParkingLot> filteredByRadius;
    public RadiusFilter() {
        this.filteredByRadius = new ArrayList<ParkingLot>();
    }
    public List<ParkingLot> filter(int radius, double latitude, double longitude, List<ParkingLot> parkingLots) {
        filteredByRadius.clear();
        for (ParkingLot parkingLot : parkingLots) { // Iterating through parking lots filtered based on proximity
            double[] latLongLot = parkingLot.getLatitudeLongitude();
            double distance = Math.hypot(latitude - latLongLot[0], longitude - latLongLot[1]);
            if (distance <= radius) {
                filteredByRadius.add(parkingLot);
            }
        }

        return filteredByRadius;
    }
}
