package entity;


import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLot {
    private String ID;
    private String streetAddress;
    private String carParkType;
    private String linkToWebsite;
    private double[] latitudeLongitude;
    private String halfHourlyRate;
    private HashMap<String, String> timesToRates;
    public ArrayList<Integer> easeOfFindingReviews;
    public ArrayList<Integer> easeOfEntryReviews;

    /**
     * ParkingLot constructor
     *
     * @param ID                - the identifier for the ParkingLot object
     * @param streetAddress     - the street address of the ParkingLot location
     * @param carparkType       - the carpark type
     * @param linkToWebsite     - the link to the website that lists/owns the ParkingLot
     * @param latitudeLongitude - an array containing the latitude and longitude coordinates of the ParkingLot location
     * @param halfHourlyRate    - the daily price schedule for the ParkingLot location
     */
    public ParkingLot(String ID, String streetAddress, String linkToWebsite, double[] latitudeLongitude, String carparkType, String halfHourlyRate, HashMap<String, String> timesToRates) {
        this.ID = ID;
        this.streetAddress = streetAddress;
        this.linkToWebsite = linkToWebsite;
        this.latitudeLongitude = latitudeLongitude;
        this.carParkType = carparkType;
        this.halfHourlyRate = halfHourlyRate;
        this.timesToRates = timesToRates;
        this.easeOfFindingReviews = new ArrayList<>();
        this.easeOfEntryReviews = new ArrayList<>();
    }

    /**
     * Contructor for ParkingLot object that only takes in one argument
     * @param streetAddress - the street address of the ParkingLot location
     */
    public ParkingLot(String streetAddress){
        this.ID = null;
        this.linkToWebsite = "";
        this.latitudeLongitude = new double[2];
        this.timesToRates = new HashMap<>();
        this.streetAddress = streetAddress;
        this.halfHourlyRate = "";
        this.easeOfFindingReviews = new ArrayList<>();
        this.easeOfEntryReviews = new ArrayList<>();
    }

    /**
     * getter method for average ease of entry (EOE) review of the ParkingLot
     * @return String value of the average EOE rating
     */
    public String getEntryReview() {
        if (easeOfEntryReviews.isEmpty()) {
            return "No reviews yet";
        } else {
            int count = easeOfEntryReviews.size();
            int sum = 0;
            for (Integer review : easeOfEntryReviews) {
                sum += review;
            }
            return String.valueOf((double) sum / count);
        }
    }

    /**
     * getter method for average ease of finding (EOf) review of the ParkingLot
     * @return String value of the average EOF rating
     */
    public String getFindingReview() {
        if (easeOfFindingReviews.isEmpty()) {
            return "No reviews yet";
        } else {
            int count = easeOfFindingReviews.size();
            int sum = 0;
            for (Integer review : easeOfFindingReviews) {
                sum += review;
            }
            return String.valueOf((double) sum / count);
        }
    }

    /**
     * getter method for streetAddress of the ParkingLot
     * @return String StreetAddress of the ParkingLot
     */
    public String getAddress() {return streetAddress; }

    /**
     * getter method for the website link of the ParkingLot
     * @return String value of the StreetAddress of the ParkingLot
     */
    public String getWebsiteLink() {return linkToWebsite; }

    /**
     * getter method for the rate schedule of the ParkingLot
     * @return HashMap value of the daily rates of the ParkingLot
     */
    public HashMap<String, String> getRates() {return timesToRates; }

    /**
     * getter method for the coordinates of the ParkingLot
     * @return Array of float values of the latitude and longitude of the ParkingLot
     */
    public double[] getLatitudeLongitude() {
        return this.latitudeLongitude;
    }


    public void setId (String id) {
        this.ID = id;
    }


    public void setWebsite(String website) {
        this.linkToWebsite = website;
    }


    public void setLatLong(double[] LatLong) {
        this.latitudeLongitude = LatLong;
    }


    public void setAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }


    public void setCarparkType(String carparkType) {
        this.carParkType = carparkType;
    }


    public void setHalfHourlyRate(String halfHourlyRate) {this.halfHourlyRate = halfHourlyRate;}


    public void setTimestoRates(HashMap<String, String> timesToRates) {
        this.timesToRates = timesToRates;
    }

}
