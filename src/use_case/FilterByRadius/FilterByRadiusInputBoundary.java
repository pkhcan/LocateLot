package use_case.FilterByRadius;

import com.google.maps.errors.ApiException;

import java.io.IOException;

/**
 * Filter by radius input boundary implemented by filter by radius interactor
 */

public interface FilterByRadiusInputBoundary {

    /**
     * When implemented, executes the interactor
     * @param filterByRadiusInputData
     * @throws Exception
     */
    public void execute (FilterByRadiusInputData filterByRadiusInputData) throws Exception;
}
