package use_case.FilterByEOE;

public class EOEInputData{
    final private int minimumEOERating;

    public EOEInputData(int minimumEOERating) {
        this.minimumEOERating = minimumEOERating;
    }


    public int getMinimumEOERating() {return minimumEOERating;}
}
