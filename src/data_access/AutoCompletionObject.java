package data_access;

import com.google.maps.GeoApiContext;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AutocompletePrediction;

import java.io.IOException;

public class AutoCompletionObject {
    private static final String API_KEY = "AIzaSyDvtiMZ_9jDUQlxr7cmDqH8HU45kVCJtkM";
    private static GeoApiContext CONTEXT = new GeoApiContext.Builder().apiKey(API_KEY).build();
    private final String sessionToken;

    public AutoCompletionObject() {
        sessionToken = SessionTokenGen.generateSessionToken();
    }

    public AutocompletePrediction[] getListOfPredictions(String address) throws
            IOException, InterruptedException, ApiException {
        PlaceAutocompleteRequest response = PlacesApi.placeAutocomplete(
                CONTEXT,
                address,
                new PlaceAutocompleteRequest.SessionToken(sessionToken));

        return response.await();

    }
}
