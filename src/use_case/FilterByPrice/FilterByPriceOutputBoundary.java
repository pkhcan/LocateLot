package use_case.FilterByPrice;

public interface FilterByPriceOutputBoundary {

    /**
     * when implemented, prepares a success view with inputted output data
     * @param filterByPriceOutputData
     */
    public void prepareSuccessView(FilterByPriceOutputData filterByPriceOutputData);

    /**
     * when implemented, prepares a fail view with output message
     * @param message
     */
    void prepareFailView(String message);
}
