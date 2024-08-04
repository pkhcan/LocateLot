package interface_adapter;

import use_case.SubmitReview.SubmitReviewOutputBoundary;
import use_case.SubmitReview.SubmitReviewOutputData;
import views.ReviewView;

import javax.swing.*;

public class SubmitReviewPresenter implements SubmitReviewOutputBoundary {
    private final ReviewViewModel reviewViewModel;

    /**
     * Prepares the label to be updated based on the result of the submission
     * @param reviewViewModel The label in the GUI to be updated.
     */
    public SubmitReviewPresenter(ReviewViewModel reviewViewModel) {
        this.reviewViewModel = reviewViewModel;
    }

    /**
     * Show the submission result.
     * @param submittedReview The output data for the submitted review.
     */
    public void showSubmission(SubmitReviewOutputData submittedReview){
        ReviewState state = reviewViewModel.getState();
        state.submitted();
        state.setParkingLot(submittedReview.getParkingLotName());
        state.setRating(submittedReview.getRating());
        reviewViewModel.firePropertyChanged();
    }

    /**
     * Show that the user has not selected a parking lot yet.
     */
    public void showEmpty(){
        ReviewState state = reviewViewModel.getState();
        state.empty();
        reviewViewModel.firePropertyChanged();
    }

    /**
     * Show that the submission failed.
     */
    public void showFail(){
        ReviewState state = reviewViewModel.getState();
        state.failed();
        reviewViewModel.firePropertyChanged();
    }
}
