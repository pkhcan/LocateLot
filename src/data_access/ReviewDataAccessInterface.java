package data_access;

import entity.Review;
import use_case.SubmitReview.SubmitReviewInputInteractor.SubmitReviewFailedException;

import java.util.ArrayList;

public interface ReviewDataAccessInterface {

    /**
     * Save the review for the corresponding ParkingLot to the json file.
     * The constructor signature is not included in the interface to adhere to the ISP by allowing different
     * initializations of the concrete DAO.
     * @param parkingLotID The id of the ParkingLot
     * @param review The review object
     * @throws SubmitReviewFailedException An exception relevant to the use case interactor, is thrown when the DAO
     *                                     fails to write the review to the json file.
     */
    void saveReview(int parkingLotID, Review review) throws SubmitReviewFailedException;

    /**
     * Get the reviews for a certain Parking Lot
     * @param parkingLotID the id for the parking lot
     * @return a list of Integers that are the reviews
     * @throws SubmitReviewFailedException if a problem arises when accessing the object. The user is not notified and
     * an empty list is returned.
     */
    ArrayList<Integer> getReviews(int parkingLotID) throws SubmitReviewFailedException;
}
