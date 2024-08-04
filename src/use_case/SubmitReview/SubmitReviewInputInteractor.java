package use_case.SubmitReview;


import data_access.ReviewDataAccessInterface;
import entity.Review;
import interface_adapter.SubmitReviewPresenter;

public class SubmitReviewInputInteractor implements SubmitReviewInputBoundary {

    final ReviewDataAccessInterface reviewDAO;
    final SubmitReviewOutputBoundary presenter;

    /**
     * Construct a {@code SubmitReviewInteractor} for the specified data access object and output boundary
     * @param reviewDAO The data access object that will access the data
     * @param outputBoundary The output boundary to present the result of the submission
     */
    public SubmitReviewInputInteractor(ReviewDataAccessInterface reviewDAO,
                                       SubmitReviewOutputBoundary outputBoundary) {
        this.reviewDAO = reviewDAO;
        this.presenter = outputBoundary;
    }

    /**
     * Submit the review and update the relevant presenter.
     * @param submitReviewInputData The input data associated with the review to be submitted
     */
    public void execute(SubmitReviewInputData submitReviewInputData){
        // create a new review object
        Review review = new Review(submitReviewInputData.getRating());

        try{
            reviewDAO.saveReview(submitReviewInputData.getParkingLotID(), review);
            System.out.println("Review of "+ submitReviewInputData.getRating() +
                    " has been saved for parking lot with id " + submitReviewInputData.getParkingLotID());

            SubmitReviewOutputData output = new SubmitReviewOutputData(submitReviewInputData.getParkingLotID(),
                    submitReviewInputData.getRating());

            presenter.showSubmission(output);
        }
        catch(Exception e){
            System.out.println("Failed to save the review");
        }
    }

    /**
     * Is thrown when a DAO fails to save the review for the corresponding ParkingLot.
     */
    public static class SubmitReviewFailedException extends Exception{
        public SubmitReviewFailedException(String message) throws SubmitReviewFailedException {
            throw new SubmitReviewFailedException(message);
        }
    }
}