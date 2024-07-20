package use_case.FilterByEOE;

import com.google.maps.errors.ApiException;

import java.io.IOException;

/**
 * Represents an input boundary for processing EOE (Ease of Entry Reviews) input data.
 * Implementing classes are responsible for executing operations based on the provided input data.
 */
public interface EOEInputBoundary {

    /**
     * Executes an operation (result sorting) based on the provided EOE input data.
     *
     * @param eoeInputData the input data related to EOE
     */
    void execute(EOEInputData eoeInputData) throws IOException, InterruptedException, ApiException;

}
