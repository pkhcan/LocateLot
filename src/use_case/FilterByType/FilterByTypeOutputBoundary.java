package use_case.FilterByType;

import use_case.FilterByType.FilterByTypeOutputData;

public interface FilterByTypeOutputBoundary {
    // Prepares either a success message or a fail message
    public void prepareSuccessView(FilterByTypeOutputData filterByTypeOutputData);

    void prepareFailView(String message);
}
