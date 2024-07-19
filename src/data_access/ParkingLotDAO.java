package data_access;
import com.google.maps.model.GeocodingResult;
import entity.ParkingLot;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class ParkingLotDAO implements GreenPDAO {

    private ArrayList<ParkingLot> parkingLots;

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
                String id = parkingLot.get("id").toString();
                String website = parkingLot.get("slug").toString();
                float[] latLong = { Float.parseFloat(parkingLot.get("lat").toString()),
                        Float.parseFloat(parkingLot.get("lng").toString())
                };
                String streetAddress = parkingLot.get("address").toString();
                // Will we use halfHourlyRate?
                String halfHourlyRate = parkingLot.get("rate_half_hour").toString();
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
                        timesToRates.put(when, rateValue);

                    }
                }


                ParkingLotFactory parkingLotFactory = new ParkingLotFactory();
                ParkingLot newParkingLot = parkingLotFactory.createParkingLot(id, website, latLong, streetAddress, timesToRates);
                parkingLots.add(newParkingLot);

            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<ParkingLot> getParkingLots() {
        return this.parkingLots;
    }

    @Override
    public ArrayList<ParkingLot> getParkingLotsPrice(double price) {
        for (int i = 0; i < parkingLots.size(); i++) {
            ParkingLot parkingLot = parkingLots.get(i);
        }
        // TODO: Implement method (should iterate through parkingLots)

        return null;
    }

    @Override
    public ArrayList<ParkingLot> getParkingLotsRadius(float radius) {
        // TODO: Implement method (should iterate through parkingLots)
        return null;
    }

    public ParkingLot getClosestParkingLot(double latitude, double longitude) {
        if(this.parkingLots == null || this.parkingLots.isEmpty()) return null;
        ParkingLot closest = null;
        double smallestDistance = Double.MAX_VALUE;

        for(ParkingLot parkingLot : this.parkingLots) {
            float[] latLong = parkingLot.getLatitudeLongitude();
            double distance = Math.hypot(latLong[0] - latitude, latLong[1] - longitude);

            if(distance < smallestDistance) {
                smallestDistance = distance;
                closest = parkingLot;
            }
        }

        return closest;
    }

    public ParkingLot getClosestParkingLot(String address) {
        try {
            GeocodingResult[] result = GeoApiDAO.getLatitudeLongitude(address);
            return getClosestParkingLot(result[0].geometry.location.lat, result[0].geometry.location.lng);
        }
        catch(Exception e) {
            System.out.println("Error getting closest parking lot");
            System.out.println(e.getMessage());
            return null;
        }
    }
}
