package use_case.FilterByEOF;

import java.time.LocalTime;

public class EOFInputData {

    final private int minimumEOFRating;
    final private LocalTime time;

    public EOFInputData(int minimumEOFRating, LocalTime time) {
        this.minimumEOFRating = minimumEOFRating;
        this.time = time;
    }

    public int getMinimumEOFRating() {return minimumEOFRating;}

    public LocalTime getTime() {return time;}
}
