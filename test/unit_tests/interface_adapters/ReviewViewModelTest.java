package unit_tests.interface_adapters;
import interface_adapter.ReviewViewModel;
import interface_adapter.SubmitReviewPresenter;
import org.junit.jupiter.api.Test;
import use_case.SubmitReview.SubmitReviewOutputData;
import views.ReviewView;

import javax.swing.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewViewModelTest {
    private ReviewViewModel viewModel = new ReviewViewModel();
    private SubmitReviewPresenter presenter = new SubmitReviewPresenter(viewModel);
    private ReviewView view = new ReviewView(viewModel);

    /**
     * ViewModel has only one test testing its basic functionality. There are no edge cases or branches.
     */
    @Test
    void testViewModel(){
        try {
            presenter.showSubmission(new SubmitReviewOutputData("A", 1));

            Field messageLabelField = ReviewView.class.getDeclaredField("messageLabel");
            messageLabelField.setAccessible(true);

            JLabel messageLabel = (JLabel) messageLabelField.get(view);
            assertEquals("Your review of " + 1 +
                    "/5 for parking lot " + "A" + " was submitted", messageLabel.getText());
        }
        catch (Exception e){
            fail();
        }
    }


}
