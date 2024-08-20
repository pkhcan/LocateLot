package unit_tests.interface_adapters;
import entity.ParkingLot;
import interface_adapter.ReviewState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewStateTest {
    /**
     * Test all the functionality of the ReviewState class in a single test
     */
    @Test
    void testReviewState() {
        // Test that different states update
        ReviewState reviewState = new ReviewState();
        assertEquals("AskForRating", reviewState.getState());

        reviewState.submitted();
        assertEquals("Submitted", reviewState.getState());

        reviewState.failed();
        assertEquals("Failed", reviewState.getState());

        reviewState.empty();
        assertEquals("Empty", reviewState.getState());

        // Test that can set and retrieve the parking lot
        reviewState.setParkingLot("Test Parking Lot");
        assertEquals("Test Parking Lot", reviewState.getParkingLot());

        // Test that can set and retrieve the rating
        reviewState.setRating(5);
        assertEquals(5, reviewState.getRating());
    }
}
