package data_access;

import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.LatLng;
import com.google.maps.PlacesApi;
import com.google.maps.PlaceAutocompleteRequest;


import java.io.IOException;

public class GeoApiDAO {
    private static final String API_KEY = "AIzaSyDeW__9cEG18wREriPhITvHPfdpMP4nQa8";
    private static GeoApiContext CONTEXT = new GeoApiContext.Builder().apiKey(API_KEY).build();

    public static GeocodingResult[] getLatitudeLongitude(String address) throws IOException, InterruptedException, ApiException {
        return GeocodingApi.newRequest(CONTEXT).address(address).await();
    }

}
