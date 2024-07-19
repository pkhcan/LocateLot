package use_case.FilterByProximity;

public class FilterByProximityInteractor implements FilterByProximityInputBoundary{
     final FilterByProximityInputData proximityInputData;

    public FilterByProximityInteractor(FilterByProximityInputData proximityInputData) {
        this.proximityInputData = proximityInputData;
    }

    @Override
    public void filter(int radius, FilterByProximityInputData proximityInputData){
    }
}
