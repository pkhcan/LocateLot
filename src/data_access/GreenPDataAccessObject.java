package data_access;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GreenPDataAccessObject {

    public static void main(String[] args) throws IOException, ParseException {
        JSONParser jsonparser = new JSONParser();
        FileReader reader = new FileReader("src/external_data/GreenPSourceData.json");
        Object obj = jsonparser.parse(reader);
        JSONObject jsonObject = (JSONObject) obj;

    }
}
        // an array of all carparks/parkinglots within the JSON file
        // JSONArray jsonArray = (JSONArray) jsonObject.get("carparks");

        // How we want to do this:
        // 1. We will parse and extract all the parking lots.
        // format of the data in the json file: one giant array with only one key and value: {carparks: [Giant list of
        // dictionaries, each dictionary holding information about a different parking lot]
        // {carparks: [{id: "1", rate: "2.5"}, {id: "2", rate: "5"}]}

        // 2. We will create a massive array containing the dictionaries that hold information about each lot. (what
        // I initially thought) OR we will create a massive dictionary that holds each attribute of the parking lot
        // as the key and a dictionary of the different lots and that specific attribute as the value. It would look
        // like this: {"pricing": {1: number, 2: number}, "location": {1: [lat, long], 2: [lat, long]}} (Nikoo's idea)

        // 3. We will create methods to get each specific data from our massive array.

        //4. the methods will be: getID(), getAddress(), getLatLon(), getRate(),getCarParkType(),
        // note for getRate(): Rates for all lots are given for each half hour.
        // note for getCarParkType(): This is for whether we want to indicate if the lot is underground or surface.

        //My questions:
        // - Do we make the attributes, attributes or classes (objects) of their own? We need to discuss with the use
        // case people to see what format they want the information in. (discuss w group)
        // - Do we need specific methods to get this data in specific formats for each use case or is that the job of
        // the usecase itself? (discuss w group)
        // - Is there a specific way we need to sort our data? (discuss w group)
        // - If we choose to make each attribute be an object of its own, will we be using an interface? (Discuss w
        // Nikoo)



        // GreenPDataAccessObject filterPriceData = GreenPDataAccessObject.MethodForPrice()
