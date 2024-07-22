package use_case.FilterByRadius;

/**
 * filter by radius output boundary interface implemented by filter by radius presenter class.
 */
public interface FilterByRadiusOutputBoundary {

    /**
     * when implemented, prepares a success view with inputted output data
     * @param filterByRadiusOutputData
     */
    public void prepareSuccessView(FilterByRadiusOutputData filterByRadiusOutputData);

    /**
     * when implemented, prepares a fail view with output message
     * @param message
     */
    void prepareFailView(String message);
}
