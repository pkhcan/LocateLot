package use_case.FilterByPrice;

import com.google.maps.errors.ApiException;

import java.io.IOException;

public interface FilterByPriceInputBoundary {
    void execute(FilterByPriceInputData filterByPriceInputData) throws IOException, InterruptedException, ApiException;

}
