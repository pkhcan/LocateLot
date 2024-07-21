package data_access;

import com.google.maps.GeoApiContext;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AutocompletePrediction;

import java.io.IOException;

public class AutoCompletionDAO {
//    private static final String API_KEY = "";
    private static final String API_KEY = System.getenv("API_KEY");
    private static GeoApiContext CONTEXT = new GeoApiContext.Builder().apiKey(API_KEY).build();
    private final String sessionToken;

    /**
     * An object to keep track of the search results for address auto-completion and its SessionToken
     */
    public AutoCompletionDAO() {sessionToken = SessionTokenGen.generateSessionToken();}

    /**
     * Return the array of the predictions matching the address
     * @param address The partially-complete/complete address
     * @return A list of predictions
     * @throws IOException
     * @throws InterruptedException
     * @throws ApiException
     */
    public AutocompletePrediction[] getListOfPredictions(String address) throws
            IOException, InterruptedException, ApiException {
        System.out.println();
        PlaceAutocompleteRequest response = PlacesApi.placeAutocomplete(
                CONTEXT,
                address,
                new PlaceAutocompleteRequest.SessionToken(sessionToken));

        return response.await();

    }
}
