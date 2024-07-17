package use_case.FilterByEOE;

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
    void execute(EOEInputData eoeInputData);

}
