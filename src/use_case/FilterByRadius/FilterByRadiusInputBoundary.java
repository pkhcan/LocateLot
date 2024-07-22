package use_case.FilterByRadius;

import com.google.maps.errors.ApiException;

import java.io.IOException;

public interface FilterByRadiusInputBoundary {
    public void execute (FilterByRadiusInputData filterByRadiusInputData) throws Exception;
}
