package entity;

import java.util.ArrayList;
import java.util.List;

public class TypeFilter {
    public List<ParkingLot> filter(String type, ArrayList<ParkingLot> parkingLots) {
        List<ParkingLot> filteredParkingLots = new ArrayList<>();

        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getType().equalsIgnoreCase(type)) {
                filteredParkingLots.add(parkingLot);
            }
        }

        return filteredParkingLots;
    }
}
