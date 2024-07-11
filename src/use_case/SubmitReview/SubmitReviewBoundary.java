package use_case.SubmitReview;

import entity.Review;

public interface SubmitReviewBoundary {

    public void submitReview(ReviewInputData reviewInputData);
}
