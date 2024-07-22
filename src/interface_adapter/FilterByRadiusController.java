//package interface_adapter;
//
//import entity.ParkingLot;
//import use_case.FilterByProximity.FilterByProximityInputBoundary;
//import use_case.FilterByProximity.FilterByProximityInputData;
//import use_case.FilterByRadius.FilterByRadiusInputBoundary;
//import use_case.FilterByRadius.FilterByRadiusInputData;
//import use_case.FilterByProximity.FilterByProximityOutputData;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FilterByRadiusController {
//
//    private final FilterByRadiusInputBoundary filterByRadiusInteractor;
//
//    public FilterByRadiusController(FilterByRadiusInputBoundary filterByRadiusInteractor) {
//        this.filterByRadiusInteractor = filterByRadiusInteractor;
////        this.filterByProximityInteractor = filterByProximityInteractor;
//    }
//
//    public void execute(int radius, String address) {
////        FilterByProximityInputData filterByProximityInputData = new FilterByProximityInputData(latLong);
////        FilterByProximityOutputData filterByProximityOutputData =
////                filterByProximityInteractor.execute(filterByProximityInputData);
////        // Gives filterByRadiusInteractor a sorted list of parking lots so that filterByRadius only filters by Radius
////        List<ParkingLot> parkingLots = filterByProximityOutputData.getParkingLots();
//        FilterByRadiusInputData filterByRadiusInputData = new FilterByRadiusInputData(radius, address);
//        try {
//            filterByRadiusInteractor.execute(filterByRadiusInputData);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
