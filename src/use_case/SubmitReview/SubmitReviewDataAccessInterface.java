package use_case.SubmitReview;

import entity.Review;
import use_case.SubmitReview.SubmitReviewInteractor.SubmitReviewFailedException;

public interface SubmitReviewDataAccessInterface{

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
}
