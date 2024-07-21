package data_access;

import com.google.maps.model.GeocodingResult;
import entity.ParkingLot;
import entity.ParkingLotFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Parking lot dao.
 */
public class ParkingLotDAO implements GreenPDAO {

    private ArrayList<ParkingLot> parkingLots;

    /**
     * Instantiates a new Parking lot dao.
     *
     * @throws IOException the io exception
     */
    public ParkingLotDAO() throws IOException {
        this.parkingLots = new ArrayList<>();
        parseFile();
    }

    private void parseFile() throws IOException {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("src/external_data/GreenPSourceData.json");
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            final JSONArray jsonArray = (JSONArray) jsonObject.get("carparks");

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject parkingLot = (JSONObject) jsonArray.get(i);

                String id = parseId(parkingLot);
                String website = parseWebsite(parkingLot);
                double[] latLong = parseLatLong(parkingLot);
                int capacity = parseCapacity(parkingLot);
                String streetAddress = parseStreetAddress(parkingLot);
                String carparkType = parseCarparkType(parkingLot);
                HashMap<String, String> timesToRates = parseTimesToRates(parkingLot);
                String halfHourlyRate = parseHalfHourlyRate(parkingLot);
//                System.out.println(timesToRates);
//                System.out.println(halfHourlyRate);

                ParkingLotFactory parkingLotFactory = new ParkingLotFactory();
                ParkingLot newParkingLot = parkingLotFactory.createParkingLot(id, website,carparkType, latLong, streetAddress, halfHourlyRate, timesToRates, capacity);
                parkingLots.add(newParkingLot);
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }
    }


    private String parseId(JSONObject parkingLot) {
        return parkingLot.get("id").toString();
    }


    private String parseWebsite(JSONObject parkingLot) {
        return parkingLot.get("slug").toString();
    }

    private double[] parseLatLong(JSONObject parkingLot) {
        return new double[]{
                Double.parseDouble(parkingLot.get("lat").toString()),
                Double.parseDouble(parkingLot.get("lng").toString())
        };
    }

    private String parseStreetAddress(JSONObject parkingLot) {
        return parkingLot.get("address").toString();
    }

    private String parseHalfHourlyRate(JSONObject parkingLot) {
        return parkingLot.get("rate_half_hour").toString();
    }

    private String parseCarparkType(JSONObject parkingLot) {
        return parkingLot.get("carpark_type_str").toString();
    }

    private int parseCapacity(JSONObject parkingLot) {
        try {
            return Integer.parseInt(parkingLot.get("capacity").toString());
        }
        catch(Exception ex) {
            return 0;
        }
    }

    private HashMap<String, String> parseTimesToRates(JSONObject parkingLot) {
        HashMap<String, String> timesToRates = new HashMap<>();
        JSONObject rateDetails = (JSONObject) parkingLot.get("rate_details");
        JSONArray periods = (JSONArray) rateDetails.get("periods");
        for (int j = 0; j < periods.size(); j++) {
            JSONObject period = (JSONObject) periods.get(j);
            JSONArray rates = (JSONArray) period.get("rates");

            for (int k = 0; k < rates.size(); k++) {
                JSONObject rate = (JSONObject) rates.get(k);
                String when = rate.get("when").toString();
                String rateValue = rate.get("rate").toString();

                if ("Day Maximum (7am - 6pm)".equals(when) || "Night Maximum (6pm - 7am)".equals(when)) {
                    timesToRates.put(when, rateValue);
                }
            }
        }
        return timesToRates;
    }


    @Override
    public ArrayList<ParkingLot> getParkingLots() {
        return this.parkingLots;
    }

    @Override
    public ArrayList<ParkingLot> getParkingLotsPrice(double price) {
        // TODO: Implement method (should iterate through parkingLots)
        return null;
    }

    @Override
    public ArrayList<ParkingLot> getParkingLotsRadius(float radius) {
        // TODO: Implement method (should iterate through parkingLots)
        return null;
    }

    /**
     * Gets closest parking lot.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     * @return the closest parking lot
     */
    public ParkingLot getClosestParkingLot(double latitude, double longitude) {
        if (this.parkingLots == null || this.parkingLots.isEmpty()) return null;
        ParkingLot closest = null;
        double smallestDistance = Double.MAX_VALUE;

        for (ParkingLot parkingLot : this.parkingLots) {
            double[] latLong = parkingLot.getLatitudeLongitude();
            double distance = Math.hypot(latLong[0] - latitude, latLong[1] - longitude);

            if (distance < smallestDistance) {
                smallestDistance = distance;
                closest = parkingLot;
            }
        }

        return closest;
    }

    /**
     * Gets closest parking lot.
     *
     * @param address the address
     * @return the closest parking lot
     */
    public ParkingLot getClosestParkingLot(String address) {
        try {
            GeocodingResult[] result = GeoApiDAO.getLatitudeLongitude(address);
            return getClosestParkingLot(result[0].geometry.location.lat, result[0].geometry.location.lng);
        }
        catch (Exception e) {
            System.out.println("Error getting closest parking lot");
            System.out.println(e.getMessage());
            return null;
        }
    }
}
