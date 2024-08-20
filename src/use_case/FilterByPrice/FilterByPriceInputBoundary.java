package use_case.FilterByPrice;

import com.google.maps.errors.ApiException;

import java.io.IOException;

/**
 * Represents an input boundary for processing filter by price input data.
 * Implementing classes are responsible for executing operations based on the provided input data.
 */
public interface FilterByPriceInputBoundary {

    /**
     * Executes an operation (result sorting) based on the provided filter by price input data.
     *
     * @param filterByPriceInputData the input data related to price filter
     */
    void execute(FilterByPriceInputData filterByPriceInputData) throws IOException, InterruptedException, ApiException;

}
