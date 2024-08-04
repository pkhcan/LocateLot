package use_case.SubmitReview;


import entity.ParkingLot;

/**
 * Represents input data to submit a review.
 */
public class SubmitReviewInputData {

    private final int rating;
    private final ParkingLot parkingLot;

    /**
     * Constructs an {@code ReviewInputData} object.
     * @param parkingLot The of the ParkingLot for which the review is.
     * @param rating The rating of the review.
     */
    public SubmitReviewInputData(ParkingLot parkingLot, int rating){

        this.parkingLot = parkingLot;
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
    public int getParkingLotID() {return Integer.parseInt(parkingLot.getID());}
}
