package data_access;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import java.io.IOException;

public class GeoApiDAO {
    private static final String API_KEY = System.getenv("API_KEY");
    private static GeoApiContext CONTEXT = new GeoApiContext.Builder().apiKey(API_KEY).build();

    public static GeocodingResult[] getLatitudeLongitude(String address) throws IOException, InterruptedException, ApiException {
        return GeocodingApi.newRequest(CONTEXT).address(address).await();
    }

}
