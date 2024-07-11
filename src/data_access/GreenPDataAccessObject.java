package data_access;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GreenPDataAccessObject {
    public static void main(String[] args) throws IOException, ParseException {
        JSONParser jsonparser = new JSONParser();
        FileReader reader = new FileReader("/Users/parmis/CSC207/LocateLot/src/GreenPSourceData.json");
        Object obj = jsonparser.parse(reader);

    }

}

