package unit_tests.interface_adapters;
import data_access.InMemoryReviewDAO;
import data_access.ReviewDataAccessInterface;
import entity.ParkingLot;
import interface_adapter.ReviewViewModel;
import interface_adapter.SubmitReviewController;
import interface_adapter.SubmitReviewPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;
import use_case.SubmitReview.SubmitReviewInputBoundary;
import use_case.SubmitReview.SubmitReviewInputInteractor;
import use_case.SubmitReview.SubmitReviewOutputBoundary;
import views.ReviewView;
import interface_adapter.ReviewState;

import javax.swing.*;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * These test suites are end-to-end tests, as the view module involves both the start and the end of the flow of the program.
 */
public class ReviewViewTest {
    // Dependencies are attributes as they are access very often in different methods
    private ReviewViewModel reviewViewModel;
    private ReviewDataAccessInterface reviewDataAccessObject;
    private SubmitReviewOutputBoundary submitReviewPresenter;
    private SubmitReviewInputBoundary submitReviewInteractor;
    private SubmitReviewController controller;
    private ReviewView reviewView;

    /**
     * Initialize the attributes and their dependencies
     */
    @BeforeEach
    public void setUp() {
        reviewViewModel = new ReviewViewModel();
        reviewDataAccessObject = new InMemoryReviewDAO();
        submitReviewPresenter = new SubmitReviewPresenter(reviewViewModel);
        submitReviewInteractor = new SubmitReviewInputInteractor(
                reviewDataAccessObject,
                submitReviewPresenter
        );
        controller = new SubmitReviewController(submitReviewInteractor);

        reviewView = new ReviewView(controller, reviewViewModel);
    }

    /**
     * Test the view model for a scenario with a successful submission.
     */
    @Test
    void testReviewViewSuccess() {

        try {
            // Testing the submit button here
            Field submitButtonField = ReviewView.class.getDeclaredField("submitButton");
            submitButtonField.setAccessible(true);
            JButton submitButton = (JButton) submitButtonField.get(reviewView);
            submitButton.doClick();

            ParkingLot testParkingLot = new ParkingLot("0", "A", "A", new double[]{0d, 0d}, "surface", "0", new HashMap<>(), 2);
            controller.submit(testParkingLot, 3);

            Field messageLabelField = ReviewView.class.getDeclaredField("messageLabel");
            messageLabelField.setAccessible(true);

            Method ask = ReviewView.class.getDeclaredMethod("showAskForRating");
            ask.setAccessible(true);
            ask.invoke(reviewView);

            JLabel messageLabel = (JLabel) messageLabelField.get(reviewView);
            assertEquals("Your review of " + 3 +
                    "/5 for parking lot " + "A" + " was submitted", messageLabel.getText());

        } catch (Exception e) {
            assert false;
        }
    }

    /**
     * Test the view model for a scenario with a failed submission.
     */
    @Test
    void testReviewViewFail() {

        ReviewViewModel reviewViewModel = new ReviewViewModel();

        ReviewDataAccessInterface reviewDataAcessObject = new InMemoryReviewDAO();
        SubmitReviewOutputBoundary submitReviewPresenter = new SubmitReviewPresenter(reviewViewModel);
        SubmitReviewInputBoundary submitReviewInputBoundary = new SubmitReviewInputInteractor(
                reviewDataAcessObject,
                submitReviewPresenter
        );
        SubmitReviewController controller = new SubmitReviewController(submitReviewInputBoundary);

        ReviewView reviewView = new ReviewView(controller, reviewViewModel);

        try {
            Field stateField = ReviewViewModel.class.getDeclaredField("state");
            stateField.setAccessible(true);

            Field messageLabelField = ReviewView.class.getDeclaredField("messageLabel");
            messageLabelField.setAccessible(true);

            JLabel messageLabel = (JLabel) messageLabelField.get(reviewView);

            ReviewState state = (ReviewState) stateField.get(reviewViewModel);
            state.failed();
            reviewViewModel.firePropertyChanged();

            messageLabel = (JLabel) messageLabelField.get(reviewView);
            assertEquals("Something went wrong, your review was not submitted.", messageLabel.getText());

        } catch (Exception e) {
            assert false;
        }
    }

    /**
     * Test the view model for a scenario where the user has not selected a parking lot yet but clicks submit
     */
    @Test
    void testReviewViewEmpty() {
        ReviewViewModel reviewViewModel = new ReviewViewModel();

        ReviewDataAccessInterface reviewDataAcessObject = new InMemoryReviewDAO();
        SubmitReviewOutputBoundary submitReviewPresenter = new SubmitReviewPresenter(reviewViewModel);
        SubmitReviewInputBoundary submitReviewInputBoundary = new SubmitReviewInputInteractor(
                reviewDataAcessObject,
                submitReviewPresenter
        );
        SubmitReviewController controller = new SubmitReviewController(submitReviewInputBoundary);

        ReviewView reviewView = new ReviewView(controller, reviewViewModel);

        try {
            Field stateField = ReviewViewModel.class.getDeclaredField("state");
            stateField.setAccessible(true);

            Field messageLabelField = ReviewView.class.getDeclaredField("messageLabel");
            messageLabelField.setAccessible(true);

            JLabel messageLabel = (JLabel) messageLabelField.get(reviewView);

            ReviewState state = (ReviewState) stateField.get(reviewViewModel);
            state.empty();
            reviewViewModel.firePropertyChanged();

            messageLabel = (JLabel) messageLabelField.get(reviewView);
            assertEquals("You have not selected a parking lot yet.", messageLabel.getText());

        } catch (Exception e) {
            assert false;
        }
    }

    /**
     * Test that the selected parking lot for the view updates as the user selects another parking lot
     */
    @Test
    void testSetParkingLotSuccess() {
        try {
            Field selectedParkingLotField = ReviewView.class.getDeclaredField("selectedParkingLot");
            selectedParkingLotField.setAccessible(true);

            ParkingLot selectedParkingLot = (ParkingLot) selectedParkingLotField.get(reviewView);
            assertNull(selectedParkingLot);
            ParkingLot expected = new ParkingLot("0", "A", "A", new double[]{0d, 0d},
                    "surface", "0", new HashMap<>(), 2);

            reviewView.setParkingLot(expected);
            selectedParkingLot = (ParkingLot) selectedParkingLotField.get(reviewView);

            assertEquals(expected, selectedParkingLot);

        } catch (Exception e) {
            assert false;
        }

    }

    /**
     * Test that the submit button works as expected.
     */
    @Test
    void testSubmitButton() {
        try {
            Field selectedParkingLotField = ReviewView.class.getDeclaredField("selectedParkingLot");
            selectedParkingLotField.setAccessible(true);

            ParkingLot selectedParkingLot = (ParkingLot) selectedParkingLotField.get(reviewView);
            assertNull(selectedParkingLot);
            ParkingLot expected = new ParkingLot("0", "A", "A", new double[]{0d, 0d},
                    "surface", "0", new HashMap<>(), 2);

            reviewView.setParkingLot(expected);
            selectedParkingLot = (ParkingLot) selectedParkingLotField.get(reviewView);

            assertEquals(expected, selectedParkingLot);

        } catch (Exception e) {
            assert false;
        }
    }
}
