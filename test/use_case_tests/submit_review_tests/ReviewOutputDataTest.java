package use_case_tests.submit_review_tests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import use_case.SubmitReview.SubmitReviewInputData;
import use_case.SubmitReview.SubmitReviewOutputData;

public class ReviewOutputDataTest {
    @Test
    void testReviewOutputDataSuccess() {
        SubmitReviewOutputData outputData = new SubmitReviewOutputData("A", 2);
        assertEquals("Review of 2 has been saved for parking lot with id A", outputData.toString());
        assertEquals("A", outputData.getParkingLotName());
        assertEquals(2, outputData.getRating());
    }

    @Test
    void testReviewOutputDataFail() {
        SubmitReviewOutputData outputData = new SubmitReviewOutputData();
        assertEquals("Submission Failed", outputData.toString());
    }
}
