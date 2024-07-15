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
import java.util.List;


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
                String halfHourlyRate = parkingLot.get("rate_half_hour").toString();
                HashMap<String, String> timesToRates = new HashMap<>();
                // TODO: Create this hashmap

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

//    public ArrayList<ParkingLot> getClosestParkingLotsWithinRadius(double latitude, double longitude, double radius) {
//        if (this.parkingLots == null || this.parkingLots.isEmpty()) return null;
//        List<ParkingLot> closestParkingLots = new ArrayList<>();
//        List<ParkingLot> copiedParkingLots = (ArrayList<ParkingLot>) this.parkingLots.clone();
//        for(ParkingLot parkingLot : copiedParkingLots) {
//            ParkingLot closest =
//        }
//
//
//    }

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
