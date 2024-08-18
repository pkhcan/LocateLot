package use_case.FilterByEOF;

import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import data_access.ParkingLotDAO;
import entity.EOFFilter;
import entity.Filter;
import entity.ParkingLot;
import entity.RadiusFilter;
import use_case.FilterOutput.OutputBoundary;
import use_case.FilterOutput.OutputData;

import java.io.IOException;
import java.util.List;

import static data_access.GeoApiDAO.getLatitudeLongitude;

public class EOFInteractor implements EOFInputBoundary{

    private final OutputBoundary outputBoundary;

    /**
     * Constructs an {@code EOEInteractor} with the specified output boundary.
     *
     * @param outputBoundary the output boundary for presenting EOE output data
     */
    public EOFInteractor(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }


    /**
     * Executes the EOF interactor logic based on the provided input data.
     * Steps:
     * - Retrieve filtered parking lots from {@code FilterOutputData}.
     * - Apply {@code EOFFilter} to filter parking lots based on capacity.
     * - Prepare {@code EOFOutputData} with filtered parking lots.
     * - Present the prepared output data using {@code outputBoundary}.
     *
     * @param eofInputData the input data containing EOF details
     */
    public void execute(EOFInputData eofInputData) throws IOException, InterruptedException, ApiException
    {
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        //ParkingLot[] parkingLots = parkingLotDAO.getParkingLots().toArray(new ParkingLot[0]);
        List<ParkingLot> parkingLots = parkingLotDAO.getParkingLots();
        String address = eofInputData.getAddress();
        GeocodingResult[] latLong = getLatitudeLongitude(address);
        double latitude = latLong[0].geometry.location.lat;
        double longitude = latLong[0].geometry.location.lng;

        RadiusFilter radiusFilter = new RadiusFilter();
        parkingLots = radiusFilter.filter(2.0, latitude, longitude, parkingLots);

        ParkingLot[] parkingLotArray = parkingLots.toArray(new ParkingLot[0]);
        Filter entryFilter = new EOFFilter();
        entryFilter.filter(parkingLotArray);

        OutputData outputData = new OutputData(parkingLotArray);
        // Present output data
        outputBoundary.present(outputData);
    }

    @Override
    public void filter(EOFInputData eofInputData){

    }
}
