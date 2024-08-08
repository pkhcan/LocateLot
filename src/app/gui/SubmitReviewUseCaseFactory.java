package app.gui;

import data_access.ReviewDAO;
import data_access.ReviewDataAccessInterface;
import interface_adapter.ReviewViewModel;
import interface_adapter.SubmitReviewController;
import interface_adapter.SubmitReviewPresenter;
import use_case.SubmitReview.SubmitReviewInputBoundary;
import use_case.SubmitReview.SubmitReviewInputInteractor;
import use_case.SubmitReview.SubmitReviewOutputBoundary;
import views.ReviewView;

import javax.swing.*;
import java.io.IOException;

public class SubmitReviewUseCaseFactory {
    /*
    To avoid instantiation.
     */
    private SubmitReviewUseCaseFactory() {}

    /**
     * Create the panel that will prompt the user to enter his/her review.
     * @return a ReviewView that will display the result of a review submission or asks for input
     */
    public static ReviewView create(){

        ReviewViewModel reviewViewModel = new ReviewViewModel();

        ReviewDataAccessInterface reviewDataAcessObject = new ReviewDAO("src/external_data/Reviews.json");
        SubmitReviewOutputBoundary submitReviewPresenter = new SubmitReviewPresenter(reviewViewModel);
        SubmitReviewInputBoundary submitReviewInputBoundary = new SubmitReviewInputInteractor(
                reviewDataAcessObject,
                submitReviewPresenter
        );
        SubmitReviewController controller = new SubmitReviewController(submitReviewInputBoundary);

        return new ReviewView(controller, reviewViewModel);

        }
    }
