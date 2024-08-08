package data_access;

import entity.Review;
import use_case.SubmitReview.SubmitReviewInputInteractor;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryReviewDAO implements ReviewDataAccessInterface {
    /**
     * Save the review for the corresponding ParkingLot to the json file.
     * The constructor signature is not included in the interface to adhere to the ISP by allowing different
     * initializations of the concrete DAO.
     *
     * @param parkingLotID The id of the ParkingLot
     * @param review The review object
     * @throws SubmitReviewInputInteractor.SubmitReviewFailedException An exception relevant to the use case interactor, is thrown when the DAO
     * fails to write the review to the json file.
     */

    private final HashMap<String, ArrayList<Integer>> reviews = new HashMap<String, ArrayList<Integer>>();

    @Override
    public void saveReview(int parkingLotID, Review review) throws SubmitReviewInputInteractor.SubmitReviewFailedException {
        if (review.getValue() < 1 || review.getValue() > 5) {
            throw new SubmitReviewInputInteractor.SubmitReviewFailedException("Review value must be between 1 and 5");
        }

        if (reviews.containsKey(parkingLotID)) {
            reviews.get(String.valueOf(parkingLotID)).add(review.getValue());
        } else {
            reviews.put(String.valueOf(parkingLotID), new ArrayList<Integer>(review.getValue()));
        }
    }


    /**
     * Get the reviews for a certain Parking Lot
     *
     * @param parkingLotID the id for the parking lot
     * @return a list of Integers that are the reviews
     * @throws SubmitReviewInputInteractor.SubmitReviewFailedException if a problem arises when accessing the object. The user is not notified and
     *                                                                 an empty list is returned.
     */
    public ArrayList<Integer> getReviews(int parkingLotID)
            throws SubmitReviewInputInteractor.SubmitReviewFailedException {
        return reviews.get(String.valueOf(parkingLotID));
    }
}
