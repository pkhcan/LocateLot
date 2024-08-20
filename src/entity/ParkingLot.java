package entity;


import data_access.ReviewDAO;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLot {
    private String ID;
    private String streetAddress;
    private String carparkType;
    private String linkToWebsite;
    private double[] latitudeLongitude;
    private String halfHourlyRate;
    private HashMap<String, String> timesToRates;
    public ArrayList<Integer> easeOfFindingReviews;
    public ArrayList<Integer> easeOfEntryReviews;
    private int capacity;

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
    public ParkingLot(String ID, String streetAddress, String linkToWebsite, double[] latitudeLongitude, String carparkType, String halfHourlyRate, HashMap<String, String> timesToRates, int capacity) {
        this.ID = ID;
        this.streetAddress = streetAddress;
        this.linkToWebsite = linkToWebsite;
        this.latitudeLongitude = latitudeLongitude;
        this.carparkType = carparkType;
        this.halfHourlyRate = halfHourlyRate;
        this.timesToRates = timesToRates;
        this.easeOfFindingReviews = new ArrayList<>();
        this.easeOfEntryReviews = new ArrayList<>();
        this.capacity = capacity;

        try {
            this.easeOfEntryReviews =
                    new ReviewDAO("src/external_data/Reviews.json").getReviews(Integer.parseInt(this.ID));
        }
        catch (Exception e) {
            this.easeOfEntryReviews = new ArrayList<>();
            System.out.println(e.getMessage());
        }
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
     * Update the reviews for this instance of parking lot
     */
    public void updateEntryReviews(){
        try {
            this.easeOfEntryReviews =
                    new ReviewDAO("src/external_data/Reviews.json").getReviews(Integer.parseInt(this.ID));
        }
        catch (Exception e) {
            this.easeOfEntryReviews = new ArrayList<>();
        }
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
     * getter method for ID of the ParkingLot
     * @return String ID of the ParkingLot
     */
    public String getID() {return ID; }

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

    /**
     * getter method for the type of ParkingLot
     * @return String value of the ParkingLot type
     */
    public String getCarParkType() { return this.carparkType; }

    /**
     * getter method for the half-hourly rate of parkingLot
     * @return String value of the ParkingLot rate
     */
    public String getHalfHourlyRate() { return this.halfHourlyRate; }

    /**
     * setter method for ParkingLot ID
     */
    public void setId (String id) {
        this.ID = id;
    }

    /**
     * setter method for ParkingLot Website
     */
    public void setWebsite(String website) {
        this.linkToWebsite = website;
    }

    /**
     * setter method for ParkingLot's lattitude and longitute values
     */
    public void setLatLong(double[] LatLong) {
        this.latitudeLongitude = LatLong;
    }

    /**
     * setter method for ParkingLot's Address
     */
    public void setAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * setter method for ParkingLot's type
     */
    public void setCarparkType(String carparkType) {
        this.carparkType = carparkType;
    }

    /**
     * setter method for ParkingLot's half-hourly rate
     */
    public void setHalfHourlyRate(String halfHourlyRate) {this.halfHourlyRate = halfHourlyRate;}

    /**
     * setter method for ParkingLot's hashmap of rates according to time.
     */
    public void setTimestoRates(HashMap<String, String> timesToRates) {
        this.timesToRates = timesToRates;
    }

    @Override
    public String toString() {
        updateEntryReviews();
        return "ID " + getID() + ": "+ getAddress() + " - " + getCarParkType() + " parking" + ", " + getHalfHourlyRate() + "$ per 30min ; " + "ease of entry: " + getEntryReview();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    /**
     * getter method for retrieving the price of the ParkingLot based on available information about the ParkingLot
     * and the client's local time.
     */
    public String getPrice(ParkingLot parkinglot, int hour) {
        String halfHourRate = parkinglot.getHalfHourlyRate();
        String dayMax = parkinglot.getRates().get("Day Maximum (7am - 6pm)");
        String nightMax = parkinglot.getRates().get("Night Maximum (6pm - 7am)");

        if (parkinglot.getRates().isEmpty()){
        return halfHourRate;
        }

        else if(dayMax !=  null && 7 <= hour && hour < 18 && Character.isDigit(dayMax.charAt(1))) {
            return dayMax;
        }
        else if (nightMax != null && Character.isDigit(nightMax.charAt(1)) && (7 > hour || hour >= 18)){
            return nightMax;
        }
        else{
            return halfHourRate;
        }

    }


    public String getType() { return  this.carparkType;
    }

}
