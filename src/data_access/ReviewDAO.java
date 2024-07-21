package data_access;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entity.Review;
import use_case.SubmitReview.SubmitReviewDataAccessInterface;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

public class ReviewDAO implements SubmitReviewDataAccessInterface {

    private final HashMap<String, List<Review>> reviews = new HashMap<String, List<Review>>();

    private final ObjectMapper objectMapper;

    private final File file;

    public ReviewDAO(String jsonPath) throws IOException {
        this.objectMapper = new ObjectMapper();
        this.file = new File(jsonPath);
    }

    public void saveReview(int parkingLotID, Review review) {
        try{
            JsonNode node = objectMapper.readTree(file);
            if (node.has(String.valueOf(parkingLotID))) {
                ArrayNode parkingLotArrayNode = (ArrayNode) node.get(parkingLotID);
                parkingLotArrayNode.add(review.getValue());
            }
            else {
                // Create a new HashMap with the new value
                Map <Integer, List<Integer>> newParkingLotMap = new HashMap<>();
                newParkingLotMap.put(parkingLotID, new ArrayList<Integer>(){{add(review.getValue());}});


                // Convert the HashMap to a JSON node and add it to the root
                JsonNode newParkingLotMapNode = objectMapper.valueToTree(newParkingLotMap);
                ((ObjectNode)node).set(String.valueOf(parkingLotID), newParkingLotMapNode);
            }

            objectMapper.writeValue(file, node);
        }
        catch (IOException e){

        }
    }

    private void save(Review review) {

    }
}
