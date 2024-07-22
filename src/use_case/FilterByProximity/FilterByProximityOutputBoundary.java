package use_case.FilterByProximity;

/**
 * Filter by proximity output boundary interface, implemented by filter by proximity presenter.
 */
public interface FilterByProximityOutputBoundary {

    /**
     * When implemented, prepares a success view for the GUI
     * @param filterByProximityOutputData
     */
    void prepareSuccessView(FilterByProximityOutputData filterByProximityOutputData);

    /**
     * Whenn implemented, prepares a fail view to output a failure message
     * @param message
     */
    void prepareFailView(String message);
}
