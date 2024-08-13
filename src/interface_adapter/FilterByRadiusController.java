package interface_adapter;

import use_case.FilterByRadius.FilterByRadiusInputBoundary;
import use_case.FilterByRadius.FilterByRadiusInputData;

public class FilterByRadiusController {
    private final FilterByRadiusInputBoundary interactor;

    /**
     * FilterByRadiusController bridges FilterByRadius view and FilterByRadiusInputBoundary.
     * @param interactor
     */

    public FilterByRadiusController(FilterByRadiusInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * FilterByRadiusController execute method transforms data into input data readable by FilterByRadiusInteractor
     * and executes interactor.
     * @param radius
     * @param address
     * @throws Exception
     */

    public void execute(double radius, String address) throws Exception {
        FilterByRadiusInputData inputData = new FilterByRadiusInputData(radius, address);
        interactor.execute(inputData);
    }
}
