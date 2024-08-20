package use_case_tests.submit_review_tests;
import entity.ParkingLot;
import org.junit.jupiter.api.Test;
import use_case.SubmitReview.SubmitReviewInputData;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewInputDataTest {

    /**
     * Test the input data for a successful input
     */
    @Test
    void testReviewInputData(){
        ParkingLot testParkingLot = new ParkingLot("");
        SubmitReviewInputData submitReviewInputData = new SubmitReviewInputData(testParkingLot, 4);

        assertEquals(4, submitReviewInputData.getRating());
        assertEquals(testParkingLot, submitReviewInputData.getParkingLot());
        assertFalse(submitReviewInputData.isEmpty());

    }

    /**
     * Test the input data for an empty input (when the user has not selected a parking lot yet but click submit)
     */
    @Test
    void testReviewInputDataEmpty(){
        SubmitReviewInputData submitReviewInputData = new SubmitReviewInputData(null, 0);
        assertTrue(submitReviewInputData.isEmpty());
    }
}
