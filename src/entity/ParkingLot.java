package entity;

import Review.java;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLot {
    private final String streetAddress;
    private final String linkToWebsite;
    private final float[] latitudeLongitude;
    private final HashMap<String, String> timesToRates;
    public ArrayList<Integer> easeOfFindingReviews;
    public ArrayList<Integer> easeOfEntryReviews;

    /**
    * Requires: xxxxx
    * @param streetAddress
    */
    ParkingLot(String streetAddress) {
        this.streetAddress = streetAddress;

    }

    public String getEntryReview() {
        if (easeOfEntryReviews.isEmpty()) {
            return "No reviews yet";
        } else {
            int count = easeOfEntryReviews.size();
            int sum = 0;
            for (Integer review : easeOfEntryReviews) {
                sum += review;
            }
            return String.valueOf((float) sum / count);
        }
    }

    public String getFindingReview() {
        if (easeOfFindingReviews.isEmpty()) {
            return "No reviews yet";
        } else {
            int count = easeOfFindingReviews.size();
            int sum = 0;
            for (Integer review : easeOfFindingReviews) {
                sum += review;
            }
            return String.valueOf((float) sum / count);
        }
    }


    public String getAddress() {return streetAddress; }

    public String getWebsiteLink() {return linkToWebsite; }

    public Array<Review> getReviews() {return reviews; }

    public HashMap<String, String> getRates() {return timesToRates; }

}