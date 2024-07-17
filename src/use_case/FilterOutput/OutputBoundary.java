package use_case.FilterOutput;

/**
 * Interface for presenting output data.
 */
public interface OutputBoundary {

    /**
     * Presents the output data.
     *
     * @param outputData the output data containing results to present
     */
    void present(OutputData outputData);

}
