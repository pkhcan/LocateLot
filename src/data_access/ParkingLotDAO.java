package data_access;
import entity.ParkingLot;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ParkingLotDAO implements GreenPDAO {

    private ArrayList<ParkingLot> parkingLots;

    public void JSONParkingLotDAO() {
        this.parkingLots = new ArrayList<>();
    }

    public void parseFile() throws IOException, ParseException {
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
                List<String> latLong = new ArrayList<>();
                latLong.add(parkingLot.get("latitude").toString());
                latLong.add(parkingLot.get("longitude").toString());
                String address = parkingLot.get("address").toString();
                String halfHourlyRate = parkingLot.get("rate_half_hour").toString();
                HashMap<String, String> timesToRates = new HashMap<>();
                // TODO: Create this hashmap

                ParkingLotFactory parkingLotFactory = new ParkingLotFactory();
                ParkingLot newParkingLot = parkingLotFactory.createParkingLot(id, website, latLong, address, timesToRates);
                parkingLots.add(newParkingLot);

            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        } catch (ParseException e) {
            throw new ParseException(e.getMessage(), 0);
        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ParkingLot> getParkingLots() {
        // TODO: Implement method (should only be one line lol)
    }

    @Override
    public ArrayList<ParkingLot> getParkingLotsPrice(double price) {
        // TODO: Implement method (should iterate through parkingLots)
        return null;
    }

    public ArrayList<ParkingLot> getParkingLotsRadius (double radius) {
        // TODO: Implement method (should iterate through parkingLots)
        return null;
    }
}
