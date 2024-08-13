//package use_case_tests.filter_by_radius_tests;
//
//import com.google.maps.GeoApiContext;
//import com.google.maps.errors.ApiException;
//import com.google.maps.model.GeocodingResult;
//import com.google.maps.model.Geometry;
//import com.google.maps.model.LatLng;
//import data_access.GeoApiDAO;
//import data_access.ParkingLotDAO;
//import entity.ParkingLot;
//import interface_adapter.FilterByRadiusPresenter;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.junit.jupiter.api.extension.ExtendWith;
//import use_case.FilterByRadius.FilterByRadiusInputData;
//import use_case.FilterByRadius.FilterByRadiusInteractor;
//import use_case.FilterByRadius.FilterByRadiusOutputData;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class FilterByRadiusInteractorTest {
//
//    ArrayList<ParkingLot> mockParkingLots = new ArrayList<>();
//
//    @Mock
//    private GeoApiContext mockContext;
//
//    @Mock
//    private GeoApiDAO mockGeoApiDAO;
//
//    @Mock
//    private ParkingLotDAO parkingLotDAO;
//
//    @Mock
//    private FilterByRadiusPresenter presenter;
//
//    @InjectMocks
//    private FilterByRadiusInteractor interactor;
//
//    @BeforeEach
//    void setUp() throws IOException, InterruptedException, ApiException {
//        // Mock the geocoding result for a valid address
//        LatLng latLngMock = mock(LatLng.class);
//        latLngMock.lat = 43.6629; // latitude for test address
//        latLngMock.lng = -79.3957; // longitude for test address
//
//        GeocodingResult mockValidGeocodingResult = mock(GeocodingResult.class);
//        mockValidGeocodingResult.geometry = mock(Geometry.class);
//        mockValidGeocodingResult.geometry.location = latLngMock;
//
//        // Creating mock of GeoApiContext to avoid external dependencies
//        mockContext = mock(GeoApiContext.class);
//
//        // Return this mock result when the getLatitudeLongitude method is called with valid address
//        when(mockGeoApiDAO.getLatitudeLongitude("20 Charles Street East, Toronto, ON, Canada", mockContext = new GeoApiContext.Builder().apiKey(System.getenv("API_KEY")).build()))
//                .thenReturn(mockCoordinates);
//
//        // Create test parking lot list
//        ParkingLot parkingLot1 = new ParkingLot("20 Charles Street East");
//        ParkingLot parkingLot2 = new ParkingLot("13 Isabella Street");
//        mockParkingLots.add(parkingLot1);
//        mockParkingLots.add(parkingLot2);
//
//        // ParkingLotDAO mock to return mock list
//        when(parkingLotDAO.getParkingLots()).thenReturn(mockParkingLots);
//    }
//
//    @Test
//    void testInteractorPassesCorrectDataToPresenter() throws Exception {
//        String testAddress = "20 Charles Street East, Toronto, ON, Canada";
//        double testRadius = 2.0;
//
//        // Create input data
//        FilterByRadiusInputData inputData = new FilterByRadiusInputData(testRadius, testAddress);
//
//        interactor = new FilterByRadiusInteractor(presenter);
//
//        // Execute the interactor
//        interactor.execute(inputData);
//
//        // Capture the data passed to the presenter
//        ArgumentCaptor<FilterByRadiusOutputData> captor = ArgumentCaptor.forClass(FilterByRadiusOutputData.class);
//        verify(presenter).prepareSuccessView(captor.capture());
//
//        // Verify the output data is as expected
//        FilterByRadiusOutputData capturedOutputData = captor.getValue();
//        assertNotNull(capturedOutputData);
//        assertEquals(capturedOutputData.getParkingLots(), mockParkingLots);
//    }
//
//    @Test
//    void testInteractorHandlesInvalidAddress() throws Exception {
//        String invalidAddress = "Invalid Address";
//        double testRadius = 2.0;
//
//        // Mock the geoApiDAO to return no results for the invalid address
//        when(GeoApiDAO.getLatitudeLongitude(invalidAddress, mockContext)).thenReturn(new GeocodingResult[0]);
//
//        // Execute the interactor
//        FilterByRadiusInputData inputData = new FilterByRadiusInputData(testRadius, invalidAddress);
//        interactor.execute(inputData);
//
//        // Verify the presenter handles the failure case
//        verify(presenter).prepareFailView("No coordinates found for given address. Please ensure that " +
//                "the given address is free of spelling errors and follows the following format, for example: " +
//                "'20 Charles Street East, Toronto, ON, Canada' or '20 Charles Street East'");
//    }
//}
