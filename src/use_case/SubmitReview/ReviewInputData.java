package use_case.SubmitReview;

import com.sun.jdi.InvalidTypeException;

public class ReviewInputData {
    private final int reviewID;
    private final String reviewType;
    private final int rating;

    static int idTracker = 1;

    /**
     * Create a ReviewInputData
     * @param reviewType RI: has to be either "EOE" or "EOF"
     * @param rating
     */
    public ReviewInputData(String reviewType, int rating){
        this.reviewID = idTracker;
        idTracker++;
        this.reviewType = reviewType;
        this.rating = rating;
    }

    public int getReviewID() {return this.reviewID;};
}
