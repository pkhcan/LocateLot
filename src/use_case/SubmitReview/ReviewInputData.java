package use_case.SubmitReview;


/**
 * Represents input data to submit a review.
 */
public class ReviewInputData {

    private final int rating;
    private final int parkingLotID;

    /**
     * Constructs an {@code ReviewInputData} object.
     * @param parkingLotID The of the ParkingLot for which the review is.
     * @param rating The rating of the review.
     */
    public ReviewInputData(int parkingLotID, int rating){

        this.parkingLotID = parkingLotID;
        this.rating = rating;
    }

    /**
     * Get the rating of the review
     * @return The rating of this review input
     */
    public int getRating() {return rating;}

    /**
     * Get the id of the parking lot for which this review input is
     * @return The id of the associated parking lot
     */
    public int getParkingLotID() {return parkingLotID;}
}
