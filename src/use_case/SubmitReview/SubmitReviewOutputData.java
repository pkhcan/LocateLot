package use_case.SubmitReview;

public class SubmitReviewOutputData {
    private boolean success = false;
    private final String parkingLotID;
    private final String rating;

    /**
     * The constructor that is used when the review submission is successful.
     * @param parkingLotID The id of the parking lot for which the submission is for
     * @param rating The rating of the review
     */
    public SubmitReviewOutputData(int parkingLotID, int rating) {
        this.success = true;
        this.parkingLotID = String.valueOf(parkingLotID);
        this.rating = rating + "";
    }

    /**
     * The constructor that is used when the review submission is fails.
     */
    public SubmitReviewOutputData(){
        this.parkingLotID = "";
        this.rating = "";
    }

    /**
     * @return a string based on either the submission was successful or not
     */
    @Override
    public String toString(){
        if (success){
            return ("Review of "+ rating +
                    " has been saved for parking lot with id " + parkingLotID);
        }
        else{
            return "Submission Failed";
        }
    }
}
