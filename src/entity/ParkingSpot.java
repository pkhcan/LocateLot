package entity;

import Review.java;

class ParkingSpot {
    private final String streetAddress;
    private final String linkToWebsite;
    private final Array<float> latitudeLongitude;
    private final Array<Review> reviews;
    private final HashMap<String, String> timesToRates;

    /**
    * Requires: xxxxx
    * @param streetAddress
    */
    ParkingSpot(String streetAddress) {
        * TODO IMPLEMENT

    }

    public String getAddress() {return streetAddress; }

    public String getWebsiteLink() {return linkToWebsite; }

    public Array<Review> getReviews() {return reviews; }

    public HashMap<String, String> getRates() {return timesToRates; }

}
