package data_access;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GreenPDataAccessObject {
    //

    public static void main(String[] args) throws IOException, ParseException {
        JSONParser jsonparser = new JSONParser();
        FileReader reader = new FileReader("src/external_data/GreenPSourceData.json");
        Object obj = jsonparser.parse(reader);
        JSONObject jsonObject = (JSONObject) obj;


        // an array of all carparks/parkinglots within the JSON file
        JSONArray jsonArray = (JSONArray) jsonObject.get("carparks");

        /* Ideas of parsing
         *
         * GreenPDataAccessObject filterPriceData = GreenPDataAccessObject.MethodForPrice()
         *
         * our mini data = {"pricing": {1: number, 2: number}, "location": {1: [lat, long], 2: [lat, long]}}
         * price data =
         * lat long data =
         *
         * method does massive parsing = our mini data
         *
         * method for price ()
         * for (iterate through ); {
         *
         *
         * }
         * method for lat long
         *
         * for () {
         *
         *
         *
         * }
         *
         * method for xyz
         *
         */





    }

}

