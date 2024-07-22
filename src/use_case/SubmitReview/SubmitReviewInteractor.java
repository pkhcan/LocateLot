package use_case.SubmitReview;


import entity.Review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.FilterByEOE.EOEInteractor;

public class SubmitReviewInteractor implements SubmitReviewBoundary {

    final SubmitReviewDataAccessInterface reviewDAO;
    final SubmitReviewOutputBoundary presenter;

    private static final Logger logger = LoggerFactory.getLogger(EOEInteractor.class);

    /**
     * Construct a {@code SubmitReviewInteractor} for the specified data access object and output boundary
     * @param reviewDAO The data access object that will access the data
     * @param outputBoundary The output boundary to present the result of the submission
     */
    public SubmitReviewInteractor(SubmitReviewDataAccessInterface reviewDAO,
                                  SubmitReviewOutputBoundary outputBoundary) {
        this.reviewDAO = reviewDAO;
        this.presenter = outputBoundary;
    }

    /**
     * Submit the review and update the relevant presenter.
     * @param reviewInputData The input data associated with the review to be submitted
     */
    public void execute(ReviewInputData reviewInputData){
        // create a new review object
        Review review = new Review(reviewInputData.getRating());

        try{
            reviewDAO.saveReview(reviewInputData.getParkingLotID(), review);
            logger.info("Review of "+ reviewInputData.getRating() +
                    " has been saved for parking lot with id " + reviewInputData.getParkingLotID());
        }
        catch(Exception e){
            logger.error(e.getMessage());
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
