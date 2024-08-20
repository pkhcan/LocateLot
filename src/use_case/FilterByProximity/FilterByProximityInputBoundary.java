package use_case.FilterByProximity;

/**
 * Filter by radius input boundary implemented by filter by radius interactor
 */
public interface FilterByProximityInputBoundary {

    /**
     * When implemented, executes the interactor
     * @param filterByProximityInputData
     */
    void execute(FilterByProximityInputData filterByProximityInputData);
}
