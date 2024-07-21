package entity;

import java.util.Arrays;
import java.util.Comparator;

public class EOFFilter implements Filter {

//    - retrieve all ParkingLot objects that are to be displayed
//    - use getCapacity method to collect all the capacities
//    - sort the parking lots based on their capacities

    /**
     * Sort array of ParkingLot objects in place based on entry reviews.
     * Highest reviews at index 0, lowest at the end, with unrated lots at the end.
     *
     * @param parkingResults Array of ParkingLot objects to be sorted
     */
    public void filter(ParkingLot[] parkingResults) {
        Arrays.sort(parkingResults, new Comparator<>() {
            /**
             *
             * @param p1 the first object to be compared.
             * @param p2 the second object to be compared.
             * @return integer denoting comparison, indicating movement to be made by the comparator object
             */
            @Override
            public int compare(ParkingLot p1, ParkingLot p2) {
                return Integer.compare(p2.getCapacity(), p1.getCapacity());
            }
        });
    }
}

