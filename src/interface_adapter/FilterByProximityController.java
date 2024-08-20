package interface_adapter;

import use_case.FilterByProximity.FilterByProximityInputBoundary;
import use_case.FilterByProximity.FilterByProximityInputData;

public class FilterByProximityController {
    private final FilterByProximityInputBoundary interactor;

    /**
     * FilterByProximityController bridges FilterByProximity view and FilterByProximityInputBoundary.
     * @param interactor
     */

    public FilterByProximityController(FilterByProximityInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * FilterByProximityController execute method transforms data into input data readable by FilterByProximityInteractor
     * and executes interactor.
     * @param address
     * @throws Exception
     */

    public void execute(String address) throws Exception {
        FilterByProximityInputData inputData = new FilterByProximityInputData(address);
        interactor.execute(inputData);
    }
}
