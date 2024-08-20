package use_case_tests.submit_review_tests;
import data_access.InMemoryReviewDAO;
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
import use_case.SubmitReview.SubmitReviewOutputData;
import views.ReviewView;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewInteractorTest {
    private ReviewDataAccessInterface reviewDataAccessObject;
    private SubmitReviewOutputBoundary submitReviewPresenter;
    private SubmitReviewInputBoundary submitReviewInteractor;
    private SubmitReviewController controller;

    @Test
    void testReviewInteractorSuccess(){
        reviewDataAccessObject = new InMemoryReviewDAO();
        submitReviewPresenter = new SubmitReviewPresenter(new ReviewViewModel()){

            public String calledMethod;

            @Override
            public void showSubmission(SubmitReviewOutputData submittedReview){
                calledMethod = "showSubmission";
            }

            @Override
            public void showEmpty(){
                calledMethod = "showEmpty";
            }

            @Override
            public void showFail(){
                calledMethod = "showFail";
            }

            @Override
            public String toString(){
                return calledMethod;
            }
        };

        submitReviewInteractor = new SubmitReviewInputInteractor(
                reviewDataAccessObject,
                submitReviewPresenter
        );
        controller = new SubmitReviewController(submitReviewInteractor);

        ParkingLot testParkingLot = new ParkingLot("0", "A", "A", new double[]{0d, 0d}, "surface", "0", new HashMap<>(), 2);
        controller.submit(testParkingLot, 3);

        assertEquals("showSubmission", submitReviewPresenter.toString());
    }

    @Test
    void testReviewInteractorFail(){
        reviewDataAccessObject = new InMemoryReviewDAO(){
            @Override
            public void saveReview(int parkingLotID, Review review) throws SubmitReviewInputInteractor.SubmitReviewFailedException{
                throw new SubmitReviewInputInteractor.SubmitReviewFailedException("Something went wrong");
            }
        };

        submitReviewPresenter = new SubmitReviewPresenter(new ReviewViewModel()){

            public String calledMethod;

            @Override
            public void showSubmission(SubmitReviewOutputData submittedReview){
                calledMethod = "showSubmission";
            }

            @Override
            public void showEmpty(){
                calledMethod = "showEmpty";
            }

            @Override
            public void showFail(){
                calledMethod = "showFail";
            }

            @Override
            public String toString(){
                return calledMethod;
            }
        };

        submitReviewInteractor = new SubmitReviewInputInteractor(
                reviewDataAccessObject,
                submitReviewPresenter
        );
        controller = new SubmitReviewController(submitReviewInteractor);

        ParkingLot testParkingLot = new ParkingLot("0", "A", "A", new double[]{0d, 0d}, "surface", "0", new HashMap<>(), 2);

        controller.submit(testParkingLot, 3);

        assertEquals("showFail", submitReviewPresenter.toString());
    }

    @Test
    void testReviewInteractorEmpty(){
        reviewDataAccessObject = new InMemoryReviewDAO();
        submitReviewPresenter = new SubmitReviewPresenter(new ReviewViewModel()){

            public String calledMethod;

            @Override
            public void showSubmission(SubmitReviewOutputData submittedReview){
                calledMethod = "showSubmission";
            }

            @Override
            public void showEmpty(){
                calledMethod = "showEmpty";
            }

            @Override
            public void showFail(){
                calledMethod = "showFail";
            }

            @Override
            public String toString(){
                return calledMethod;
            }
        };

        submitReviewInteractor = new SubmitReviewInputInteractor(
                reviewDataAccessObject,
                submitReviewPresenter
        );
        controller = new SubmitReviewController(submitReviewInteractor);

        controller.submit(null, 0);

        assertEquals("showEmpty", submitReviewPresenter.toString());
    }



}
