package use_case.SubmitReview;

import interface_adapter.ReviewState;

public interface SubmitReviewOutputBoundary {
    /**
     * Show the submission result.
     * @param submittedReview The output data for the submitted review.
     */
    public void showSubmission(SubmitReviewOutputData submittedReview);

    /**
     * Show that the user has not selected a parking lot yet.
     */
    public void showEmpty();

    /**
     * Show that the submission failed.
     */
    public void showFail();
}
