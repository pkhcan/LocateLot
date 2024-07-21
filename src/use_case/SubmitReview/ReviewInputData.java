package use_case.SubmitReview;

import com.sun.jdi.InvalidTypeException;

public class ReviewInputData {

    private final int rating;
    private final int parkingLotID;

    /**
     *
     * @param parkingLotID
     * @param rating
     */
    public ReviewInputData(int parkingLotID, int rating){

        this.parkingLotID = parkingLotID;
        this.rating = rating;
    }
}
