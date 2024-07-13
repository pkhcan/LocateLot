package entity;


import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLot {
    private String ID;
    private String streetAddress;
    private String linkToWebsite;
    private float[] latitudeLongitude;
    private HashMap<String, String> timesToRates;
    public ArrayList<Integer> easeOfFindingReviews;
    public ArrayList<Integer> easeOfEntryReviews;

    /**
    * Requires: xxxxx
    * @param streetAddress
    */
    public ParkingLot(String ID, String streetAddress, String linkToWebsite, float[] latitudeLongitude, HashMap<String, String> timesToRates) {
        this.ID = ID;
        this.streetAddress = streetAddress;
        this.linkToWebsite = linkToWebsite;
        this.latitudeLongitude = latitudeLongitude;
        this.timesToRates = timesToRates;
    }

    public ParkingLot(String streetAddress){
        this.ID = null;
        this.linkToWebsite = "";
        this.latitudeLongitude = new float[2];
        this.timesToRates = new HashMap<>();
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

    public HashMap<String, String> getRates() {return timesToRates; }

    public float[] getLatitudeLongitude() {
        return this.latitudeLongitude;
    }

    public void setId (String id) {
        this.ID = id;
    }


    public void setWebsite(String website) {
        this.linkToWebsite = website;
    }


    public void setLatLong(float[] LatLong) {
        this.latitudeLongitude = LatLong;
    }


    public void setAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }


    public void setTimestoRates(HashMap<String, String> timesToRates) {
        this.timesToRates = timesToRates;
    }

}
