package use_case.FilterByEOE;

public interface EOEInputBoundary {


    void filter(int minimumEOERating);

    void filter(EOEInputData eoeInputData);
}
