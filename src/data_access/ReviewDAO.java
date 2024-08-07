package data_access;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entity.Review;
import use_case.SubmitReview.SubmitReviewInputInteractor.SubmitReviewFailedException;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;

public class ReviewDAO implements ReviewDataAccessInterface {

    private final HashMap<String, List<Review>> reviews = new HashMap<String, List<Review>>();

    private final ObjectMapper objectMapper;

    private final File file;

    /**
     * Constructs a ReviewDAO for the given path
     * Precondition: The file has to be a json file
     * @param jsonPath The path to the json file to be accessed and modified.
     * @throws IOException Thrown if the path is invalid.
     */
    public ReviewDAO(String jsonPath){
        this.objectMapper = new ObjectMapper();
        this.file = new File(jsonPath);
    }

    /**
     * Save the review for the corresponding ParkingLot to the json file.
     * @param parkingLotID The id of the ParkingLot
     * @param review The review object
     * @throws SubmitReviewFailedException An exception relevant to the use case interactor, is thrown when the DAO
     *                                     fails to write the review to the json file.
     */
    @Override
    public void saveReview(int parkingLotID, Review review) throws SubmitReviewFailedException {
        try{
            // Create the root using the object mapper
            JsonNode node = objectMapper.readTree(file);

            // Check to see either the value is in the file or not
            if (node.has(String.valueOf(parkingLotID))) {
                ArrayNode parkingLotArrayNode = (ArrayNode) node.get(String.valueOf(parkingLotID));
                parkingLotArrayNode.add(review.getValue());
            }

            // If the value is not in the json file;
            else {
                // Add the review value to an ArrayList and add it to a JSON node
                JsonNode newParkingLotMapNode = objectMapper.valueToTree(
                        new ArrayList<Integer>(){{add(review.getValue());}}
                );

                // Add the node to the root
                ((ObjectNode)node).set(String.valueOf(parkingLotID), newParkingLotMapNode);
            }

            objectMapper.writeValue(file, node);
        }

        catch (IOException e){
            throw new SubmitReviewFailedException("Failed to write the review to the json file.");
        }
    }

    public ArrayList<Integer> getReviews(int parkingLotID) throws SubmitReviewFailedException {
        try{
            ArrayList<Integer> acc = new ArrayList<Integer>();
            // Create the root using the object mapper
            JsonNode node = objectMapper.readTree(file);

            // Check to see either the value is in the file or not
            if (node.has(String.valueOf(parkingLotID))) {
                ArrayNode parkingLotArrayNode = (ArrayNode) node.get(String.valueOf(parkingLotID));
                if (parkingLotArrayNode != null) {

                    //Iterating JSON array
                    for (int i=0;i<parkingLotArrayNode.size();i++){
                        //Adding each element of JSON array into ArrayList
                        acc.add(Integer.parseInt(parkingLotArrayNode.get(i).asText()));
                    }
                }
            }

            return acc;
        }

        catch (IOException e){
            throw new SubmitReviewFailedException("Failed to write the review to the json file.");
        }
    }
}
