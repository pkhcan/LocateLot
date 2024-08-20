package entity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {
    /**
     * Test that the Review entity works as it's supposed
     */
    @Test
    void testReview(){
        Review review = new Review(3);
        assertEquals(3, review.getValue());

        review.setValue(5);
        assertEquals(5, review.getValue());
    }
}
