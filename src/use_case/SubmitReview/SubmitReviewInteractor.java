package use_case.SubmitReview;

import data_access.ReviewDAO;
import use_case.FilterOutput.OutputBoundary;

public class SubmitReviewInteractor implements SubmitReviewBoundary {




    public void execute(ReviewInputData reviewInputData){};


    /**
     * Is thrown when a DAO fails to save the review for the corresponding ParkingLot
     */
    public static class SubmitReviewFailedException extends Exception{
        public SubmitReviewFailedException(String message) throws SubmitReviewFailedException {
            throw new SubmitReviewFailedException(message);
        }
    }
}
