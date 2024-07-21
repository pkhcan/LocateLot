package interface_adapter;

import use_case.SubmitReview.SubmitReviewOutputBoundary;
import use_case.SubmitReview.SubmitReviewOutputData;

import javax.swing.*;

public class SubmitReviewPresenter implements SubmitReviewOutputBoundary {
    private SubmitReviewOutputBoundary output;
    private JLabel label;

    /**
     * Prepares the label to be updated based on the result of the submission
     * @param label The label in the GUI to be updated.
     */
    public SubmitReviewPresenter(JLabel label) {
        this.label = label;
    }

    /**
     * Show the submission result.
     * @param reviewSubmitted The output data for the submitted review.
     */
    public void showSubmission(SubmitReviewOutputData reviewSubmitted){
        label.setText(reviewSubmitted.toString());
    }
}
