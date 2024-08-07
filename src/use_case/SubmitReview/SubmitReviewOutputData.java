package use_case.SubmitReview;

public class SubmitReviewOutputData {
    private boolean success = false;
    private final String parkingLotName;
    private final String rating;

    /**
     * The constructor that is used when the review submission is successful.
     * @param parkingLotName The id of the parking lot for which the submission is for
     * @param rating The rating of the review
     */
    public SubmitReviewOutputData(String parkingLotName, int rating) {
        this.success = true;
        this.parkingLotName = parkingLotName;
        this.rating = rating + "";
    }

    /**
     * The constructor that is used when the review submission is fails.
     */
    public SubmitReviewOutputData(){
        this.parkingLotName = "";
        this.rating = "";
    }

    /**
     * @return a string based on either the submission was successful or not
     */
    @Override
    public String toString(){
        if (success){
            return ("Review of "+ rating +
                    " has been saved for parking lot with id " + parkingLotName);
        }
        else{
            return "Submission Failed";
        }
    }

    public String getParkingLotName() {return parkingLotName;}

    public int getRating() {return Integer.parseInt(rating);}
}
