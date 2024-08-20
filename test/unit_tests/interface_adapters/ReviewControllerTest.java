package unit_tests.interface_adapters;
import data_access.InMemoryReviewDAO;
import data_access.ReviewDataAccessInterface;
import entity.ParkingLot;
import interface_adapter.ReviewViewModel;
import interface_adapter.SubmitReviewController;
import interface_adapter.SubmitReviewPresenter;
import org.junit.jupiter.api.Test;
import use_case.SubmitReview.SubmitReviewInputBoundary;
import use_case.SubmitReview.SubmitReviewInputInteractor;
import use_case.SubmitReview.SubmitReviewOutputBoundary;
import views.ReviewView;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewControllerTest {

    /**
     * Test the controller for a typical scenario of review submission.
     */
    @Test
    void testReviewControllerTypicalScenario(){
        ReviewViewModel reviewViewModel = new ReviewViewModel();
        ReviewDataAccessInterface reviewDataAccessObject = new InMemoryReviewDAO();
        SubmitReviewOutputBoundary submitReviewPresenter = new SubmitReviewPresenter(reviewViewModel);
        SubmitReviewInputBoundary submitReviewInteractor = new SubmitReviewInputInteractor(
                reviewDataAccessObject,
                submitReviewPresenter
        );;
        SubmitReviewController controller = new SubmitReviewController(submitReviewInteractor);
        ReviewView reviewView = new ReviewView(controller, reviewViewModel);
        controller.submit(null, 0);

        assertEquals("Empty", reviewViewModel.getState().getState());

        ParkingLot testParkingLot = new ParkingLot("0", "B", "B", new double[]{0d, 0d}, "garage", "0", new HashMap<>(), 2);
        controller.submit(testParkingLot, 2);

        assertEquals("Submitted", reviewViewModel.getState().getState());
    }
}
