package interface_adapter;

import entity.ParkingLot;

public class ReviewState {
    private String parkingLot;
    private int rating;
    private String state;

    public ReviewState() {
        state = "AskForRating";
    }
    public void submitted(){
        this.state = "Submitted";
    }

    public void failed(){
        this.state = "Failed";
    }

    public void setParkingLot(String parkingLot) {this.parkingLot = parkingLot;}

    public void setRating(int rating) {this.rating = rating;}

    public String getParkingLot(){return parkingLot;}

    public int getRating(){return rating;}

    public String getState(){return state;}
}
