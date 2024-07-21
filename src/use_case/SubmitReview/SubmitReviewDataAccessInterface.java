package use_case.SubmitReview;

import entity.Review;

public interface SubmitReviewDataAccessInterface {
    void saveReview(int parkingLotID, Review review);
}
