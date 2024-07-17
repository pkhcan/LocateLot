package use_case.FilterByEOE;

/**
 * Interface for presenting Ease of Entry (EOE) output data.
 */
public interface EOEOutputBoundary {

    /**
     * Presents the EOE output data.
     *
     * @param outputData the output data containing EOE results to present
     */
    void present(EOEOutputData outputData);

}
