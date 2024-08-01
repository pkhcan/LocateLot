package interface_adapter;

import com.google.maps.errors.ApiException;
import use_case.FilterByEOE.EOEInputBoundary;
import use_case.FilterByEOE.EOEInputData;

import java.io.IOException;

/**
 * Controller for the EOE (Ease of Entry) use case.
 * Acts as an intermediary between the user interface and the EOE use case interactor.
 */
public class EOEController {

    private final EOEInputBoundary interactor;

    /**
     * Constructs an {@code EOEController} with the specified interactor.
     *
     * @param interactor the interactor for handling EOE use case
     */
    public EOEController(EOEInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Handles the EOE use case by creating input data and passing it to the interactor.
     *
     * @param address the address for which to filter parking lots by EOE
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     * @throws ApiException         if an API error occurs
     */
    public void handleEOE(String address) throws IOException, InterruptedException, ApiException {
        EOEInputData inputData = new EOEInputData(address);
        interactor.execute(inputData);
    }
}
