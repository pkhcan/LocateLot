package entity;

import Review.java;

import java.util.ArrayList;
import java.util.HashMap;

class ParkingLot {
    private final String streetAddress;
    private final String linkToWebsite;
    private final ArrayList<Float> latitudeLongitude;
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

    public <T> getEntryReview() {
        if (easeOfEntryReviews.isEmpty()) {
            return "null";
        }
        else {
            int count = easeOfEntryReviews.size();
            int sum = 0;
            for (int i = 0; i < count; i++) {
                sum += easeOfEntryReviews.get(i);
            }
            int avg = sum / count;
            return avg;
    }

    public String getAddress() {return streetAddress; }

    public String getWebsiteLink() {return linkToWebsite; }

    public Array<Review> getReviews() {return reviews; }

    public HashMap<String, String> getRates() {return timesToRates; }

}
