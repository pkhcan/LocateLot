package entity;

import data_access.ParkingLotDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RadiusFilter {
    List<ParkingLot> filteredByRadius;

    public RadiusFilter() {
        this.filteredByRadius = new ArrayList<ParkingLot>();
    }

    public List<ParkingLot> filter(double radius, double latitude, double longitude, List<ParkingLot> parkingLots) {
        filteredByRadius.clear();
        for (ParkingLot parkingLot : parkingLots) { // Iterating through parking lots that should already be filtered based on proximity
            double[] latLongLot = parkingLot.getLatitudeLongitude();
            double distance = calculateDistanceDegToKM(latitude, longitude, latLongLot[0], latLongLot[1]); // distance in km
//            System.out.println("Distance to " + parkingLot.getAddress() + ": " + distance + " km");
            if (distance <= radius) {
                filteredByRadius.add(parkingLot);
            }
        }
        return filteredByRadius;
    }

    /**
     * Uses haversine formula to convert distance between two coordinate points (in degrees) to distance in km.
     * Steps:
     * 1. convert latitude and longitude distances in degrees to radians
     * 2. compute half the chord length between the two coordinate points, 'a'
     * 3. use 'a' to calculate the angular distance, 'c'
     * 4. Multiply the radius of the Earth (in km) by the angular distance to get the distance between 2 points in km
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return distance between two coordinate points in km
     */
    private static double calculateDistanceDegToKM(double lat1, double lng1, double lat2, double lng2) {
        double earthRadiusKM = 6371.0; // radius of the earth in km
        double latMinusLat = Math.toRadians(lat2 - lat1); // difference between two latitudes in radians
        double lngMinusLng = Math.toRadians(lng2 - lng1); // difference between two longitudes in radians

        double a = Math.sin(latMinusLat / 2) * Math.sin(latMinusLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lngMinusLng / 2) * Math.sin(lngMinusLng / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadiusKM * c; // use angular distance and earth's radius (in km) to find distance in km.
    }
}

//    public static void main(String[] args) throws IOException {
//        List<ParkingLot> parkingLots = new ArrayList<>();
//        // Add some parking lots with known coordinates
//        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
//        ArrayList<ParkingLot> parkingLots2 = parkingLotDAO.getParkingLots();
//        parkingLots.add(parkingLotDAO.getParkingLots().get(0));
//        parkingLots.add(parkingLotDAO.getParkingLots().get(1));
//        parkingLots.add(parkingLotDAO.getParkingLots().get(2));
//        parkingLots.add(parkingLotDAO.getParkingLots().get(3));
//
//        double latLong1[] = {43.669282202140174, -79.3852894625656};
//
//        RadiusFilter radiusFilter = new RadiusFilter();
//        List<ParkingLot> filteredLots = radiusFilter.filter(1.0, 43.669282202140174,
//                -79.3852894625656, parkingLots); // 1.0 km radius from 20 Charles Street East
//
//        System.out.println("Filtered Parking Lots:");
//        for (ParkingLot lot : filteredLots) {
//            System.out.println(lot);
//        }
//    }
//}

//            if (distance <= radius) {
//                filteredByRadius.add(parkingLot);
//            }
//        }
//
//        return filteredByRadius;
//    }
//
//    /**
//     * Uses haversine formula to convert distance between two coordinate points (in degrees) to distance in km.
//     * Steps:
//     * 1. convert latitude and longitude distances in degrees to radians
//     * 2. compute half the chord length between the two coordinate points, 'a'
//     * 3. use 'a' to calculate the angular distance, 'c'
//     * 4. Multiply the radius of the Earth (in km) by the angular distance to get the distance between 2 points in km
//     * @param lat1
//     * @param lng1
//     * @param lat2
//     * @param lng2
//     * @return distance between two coordinate points in km
//     */
//    private static double calculateDistanceDegToKM(double lat1, double lng1, double lat2, double lng2) {
//        double earthRadiusKM = 6371.0; // radius of the earth in km
//        double latMinusLat = Math.toRadians(lat2 - lat1); // difference between two latitudes in radians
//        double lngMinusLng = Math.toRadians(lng2 - lng1); // difference between two longitudes in radians
//
//        // using haversine formula to find angular distance from coordinate points in degrees
//        double a = Math.sin(lngMinusLng / 2) * Math.sin(latMinusLat / 2) +
//                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
//                        Math.sin(lngMinusLng / 2) * Math.sin(lngMinusLng / 2);
//
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//
//        return earthRadiusKM * c; // use angular distance and earth's radius (in km) to find distance in km.
//    }
//}
