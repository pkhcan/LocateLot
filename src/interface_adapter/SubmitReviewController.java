package interface_adapter;

import entity.ParkingLot;
import use_case.SubmitReview.SubmitReviewInputData;
import use_case.SubmitReview.SubmitReviewInputBoundary;

public class SubmitReviewController {
    final SubmitReviewInputBoundary submitReviewInputBoundary;

    public SubmitReviewController(SubmitReviewInputBoundary submitReviewInteractor) {
        this.submitReviewInputBoundary = submitReviewInteractor;
    }

    public void execute(ParkingLot parkingLot, int rating) {
        SubmitReviewInputData submitReviewInputData = new SubmitReviewInputData(parkingLot, rating);
        submitReviewInputBoundary.execute(submitReviewInputData);
    }
}
