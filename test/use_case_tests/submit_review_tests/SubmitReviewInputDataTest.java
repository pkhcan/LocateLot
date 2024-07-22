package use_case_tests.submit_review_tests;

import org.junit.jupiter.api.Test;
import use_case.SubmitReview.ReviewInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubmitReviewInputDataTest {
    @Test
    void testSubmitReviewInputData(){
        ReviewInputData reviewInputData = new ReviewInputData(1, 5);
        assertEquals(reviewInputData.getRating(), 5);
        assertEquals(reviewInputData.getParkingLotID(), 1);
    }
}
