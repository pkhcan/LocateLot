package interface_adapter;

import entity.ParkingLot;

public class ReviewState {
    private String parkingLot;
    private int rating;
    private String state;

    /**
     * Create a new state. The state is "AskForRating" by default as the user will first be prompted to enter a rating
     * regardless of whether they have selected a parking lot or not.
     */
    public ReviewState() {state = "AskForRating";}

    /**
     * Sets the state of this ReviewState object to "Submitted"
     */
    public void submitted(){this.state = "Submitted";}

    /**
     * Sets the state of this ReviewState object to "Failed"
     */
    public void failed(){this.state = "Failed";}

    /**
     * Sets the state of this ReviewState object to "Empty"
     */
    public void empty(){this.state = "Empty";}

    /**
     * Set the name of the parking lot associated with the current state
     * @param parkingLot the String name of the parking lot for which a submission report will be shown.
     */
    public void setParkingLot(String parkingLot) {this.parkingLot = parkingLot;}

    /**
     * Set rating for the associated parking lot
     * @param rating an int that is submitted rating for the associated parking lot
     */
    public void setRating(int rating) {this.rating = rating;}

    /**
     * Get the name of the associated parking lot
     * @return a String that is the name of the associated parking lot
     */
    public String getParkingLot(){return parkingLot;}

    /**
     * Get the rating of the associated parking lot
     * @return a int that is the new rating of the associated parking lot
     */
    public int getRating(){return rating;}

    /**
     * Get the current state
     * @return a String that shows the current state
     */
    public String getState(){return state;}
}
