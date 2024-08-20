package unit_tests.interface_adapters;
import data_access.InMemoryReviewDAO;
import data_access.ReviewDAO;
import data_access.ReviewDataAccessInterface;
import entity.ParkingLot;
import entity.Review;
import interface_adapter.ReviewViewModel;
import interface_adapter.SubmitReviewController;
import interface_adapter.SubmitReviewPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.SubmitReview.SubmitReviewInputBoundary;
import use_case.SubmitReview.SubmitReviewInputInteractor;
import use_case.SubmitReview.SubmitReviewOutputBoundary;
import views.ReviewView;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class ReviewPresenterTest {
    private ReviewViewModel reviewViewModel;
    private ReviewDataAccessInterface reviewDataAccessObject;
    private SubmitReviewOutputBoundary submitReviewPresenter;
    private SubmitReviewInputBoundary submitReviewInteractor;
    private SubmitReviewController controller;
    private ReviewView reviewView;

    /**
     * Set up the dependencies needed.
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
     * Test the presenter for a successful submission
     */
    @Test
    void testReviewPresenterSubmission(){
        ParkingLot testParkingLot = new ParkingLot("0", "A", "A", new double[]{0d, 0d}, "surface", "0", new HashMap<>(), 2);
        controller.submit(testParkingLot, 3);

        assertEquals("Submitted", reviewViewModel.getState().getState());
    }

    /**
     * Test the presenter before any interaction
     */
    @Test
    void testReviewPresenterEmpty(){
        controller.submit(null, 0);

        assertEquals("Empty", reviewViewModel.getState().getState());
    }


    /**
     * Test the presenter for a failed submission
     */
    @Test void testReviewPresenterFail(){
        // A custom DAO for the failed view
        ReviewDataAccessInterface failDAO = new InMemoryReviewDAO(){
            @Override
            public void saveReview(int parkingLotID, Review review) throws SubmitReviewInputInteractor.SubmitReviewFailedException{
                throw new SubmitReviewInputInteractor.SubmitReviewFailedException("Something went wrong");
            }
        };

        submitReviewPresenter = new SubmitReviewPresenter(reviewViewModel);
        submitReviewInteractor = new SubmitReviewInputInteractor(
                failDAO,
                submitReviewPresenter
        );
        controller = new SubmitReviewController(submitReviewInteractor);

        reviewView = new ReviewView(controller, reviewViewModel);

        ParkingLot testParkingLot = new ParkingLot("0", "A", "A", new double[]{0d, 0d}, "surface", "0", new HashMap<>(), 2);
        controller.submit(testParkingLot, 3);

        assertEquals("Failed", reviewViewModel.getState().getState());

    }
}
