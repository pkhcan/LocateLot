package external_data;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import data_access.ReviewDAO;
import entity.Review;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.SubmitReview.SubmitReviewInputInteractor;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReviewDAOTest {
    private String path = "test/external_data/ReviewsTest.json";
    private Review testParingLotReview = new Review(1);
    private ReviewDAO reviewDAO = new ReviewDAO(path);
    private File file = new File(path);

    /**
     * Prepare the file for testing
     */
    @BeforeEach
    void setUp() {
        try {
            // Write the empty JSON to the file
            file.setWritable(true);

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.flush();
            fileWriter.write("{}");
            fileWriter.close();
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test the scenario where we submit the first review for a parking lot
     */
    @Test
    void testReivewOneParkingFirstReview() {
        try {
            reviewDAO.saveReview(1, testParingLotReview);
        } catch (Exception e) {
            fail();
        }

        try{
            ObjectMapper objectMapper = new ObjectMapper();

            ArrayList<Integer> acc = new ArrayList<>();
            // Create the root using the object mapper
            JsonNode node = objectMapper.readTree(file);

            ArrayNode parkingLotArrayNode = (ArrayNode) node.get(String.valueOf(1));
            assertEquals(1, Integer.parseInt(parkingLotArrayNode.get(0).asText()));
            assertEquals(1, parkingLotArrayNode.size());
            assertEquals(1, node.size());
        }

        catch (Exception e){
            fail();
        }

    }

    /**
     * Test the scenario where we submit the also a second review for a parking lot
     */
    @Test
    void testReivewOneParkingSecondReview() {
        try {
            reviewDAO.saveReview(1, testParingLotReview);
            reviewDAO.saveReview(1, testParingLotReview);
        } catch (Exception e) {
            fail();
        }

        try{
            ObjectMapper objectMapper = new ObjectMapper();

            // Create the root using the object mapper
            JsonNode node = objectMapper.readTree(file);

            ArrayNode parkingLotArrayNode = (ArrayNode) node.get(String.valueOf(1));
            assertEquals(1, Integer.parseInt(parkingLotArrayNode.get(0).asText()));
            assertEquals(1, Integer.parseInt(parkingLotArrayNode.get(1).asText()));
            assertEquals(1, node.size());
        }

        catch (Exception e){
            fail();
        }

    }

    /**
     * Test the scenario where we submit the also a second review for a parking lot
     */
    @Test
    void testReivewTwoParkings() {
        try {
            reviewDAO.saveReview(1, testParingLotReview);
            reviewDAO.saveReview(2, testParingLotReview);
        } catch (Exception e) {
            fail();
        }

        try{
            ObjectMapper objectMapper = new ObjectMapper();

            // Create the root using the object mapper
            JsonNode node = objectMapper.readTree(file);

            ArrayNode parkingLotArrayNode = (ArrayNode) node.get(String.valueOf(1));
            assertEquals(1, Integer.parseInt(parkingLotArrayNode.get(0).asText()));

            parkingLotArrayNode = (ArrayNode) node.get(String.valueOf(2));
            assertEquals(1, Integer.parseInt(parkingLotArrayNode.get(0).asText()));
            assertEquals(2, node.size());
        }

        catch (Exception e){
            fail();
        }

    }

    /**
     * Test the DAO methods with an empty file that should raise an error
     */
    @Test
    void testReivewEmptyFile() {
        try {
            // Write the empty JSON to the file
            file.setWritable(true);

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e) {
            fail(e.getMessage());
        }

        try{
            reviewDAO.saveReview(1, testParingLotReview);
            reviewDAO.getReviews(1);
            fail();
        }
        catch (Exception e){
            assertTrue(true);
        }
    }
}
