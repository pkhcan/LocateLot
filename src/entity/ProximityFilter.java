//package entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProximityFilter {
//    public ProximityFilter() {
//    }
//
//    public List<ParkingLot> filter(double latitude, double longitude, ArrayList<ParkingLot> parkingLots) {
//        List<ParkingLot> filteredByProximity = new ArrayList<ParkingLot>;
//        while (!parkingLots.isEmpty()) {
//            ParkingLot closest = parkingLotDAO.getClosestParkingLot(filterByProximityInputData.getLatLong()[0],
//                    filterByProximityInputData.getLatLong()[1]); --> // can't implement due to having to access DAO
//            parkingLots.remove(closest);
//            filteredByProximity.add(closest);
//        }
//
//        return filteredByProximity;
//    }
//}
