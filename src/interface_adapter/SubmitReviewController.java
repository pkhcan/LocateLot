package interface_adapter;

import entity.ParkingLot;
import use_case.SubmitReview.SubmitReviewInputData;
import use_case.SubmitReview.SubmitReviewInputBoundary;

public class SubmitReviewController {
    final SubmitReviewInputBoundary submitReviewInputBoundary;

    /**
     * Create a new controller to submit reviews
     * @param submitReviewInteractor a SubmitReviewInputBoundary that will process the user inputs
     */
    public SubmitReviewController(SubmitReviewInputBoundary submitReviewInteractor) {
        this.submitReviewInputBoundary = submitReviewInteractor;
    }

    /**
     * Submit the review
     * @param parkingLot the associated parking lot
     * @param rating the new rating for the associated parking lot
     */
    public void submit(ParkingLot parkingLot, int rating) {
        SubmitReviewInputData submitReviewInputData = new SubmitReviewInputData(parkingLot, rating);
        submitReviewInputBoundary.execute(submitReviewInputData);
    }
}
