package use_case_tests.submit_review_tests;

import interface_adapter.SubmitReviewPresenter;
import org.junit.jupiter.api.Test;
import use_case.SubmitReview.SubmitReviewOutputData;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class SubmitReviewPresenterTest {
    @Test
    void testSuccessfulSubmitReviewPresenter() {
        // set up the fake GUI and the label
        JLabel fakeLabel = new JLabel();

        // set up the presenter
        SubmitReviewPresenter presenter = new SubmitReviewPresenter(fakeLabel);

        // set up a fake successful input for the presenter
        SubmitReviewOutputData input = new SubmitReviewOutputData(1, 1);

        presenter.showSubmission(input);

        String expected = "Review of 1 has been saved for parking lot with id 1";
        String actual = fakeLabel.getText();
        assertEquals(expected, actual);

    }

    @Test
    void testFailedSubmitReviewPresenter() {
        // set up the fake GUI and the label
        JLabel fakeLabel = new JLabel();

        // set up the presenter
        SubmitReviewPresenter presenter = new SubmitReviewPresenter(fakeLabel);

        // set up a fake failed input for the presenter
        SubmitReviewOutputData input = new SubmitReviewOutputData();

        presenter.showSubmission(input);

        String expected = "Submission Failed";
        String actual = fakeLabel.getText();
        assertEquals(expected, actual);

    }
}
