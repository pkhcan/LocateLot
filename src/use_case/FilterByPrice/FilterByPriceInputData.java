package use_case.FilterByPrice;

import data_access.ParkingLotDAO;
import java.time.LocalTime;
import java.util.ArrayList;
import entity.ParkingLot;
//import use_case.FilterByRadius;

public class FilterByPriceInputData {

    final LocalTime time;
    final ArrayList<ParkingLot> filteredByRadius;

    public FilterByPriceInputData(LocalTime time, ArrayList<ParkingLot> filteredByRadius) {
        this.time = time;
        this.filteredByRadius = filteredByRadius;
    }

    /**
     *
     * @return the time of the desired parking lot to get the price range at
     */
    public LocalTime getTime() {return time;}
}

    // Use the method from nikoo_sandbox, FilterByRadiusInteractor.java that says execute:
    // filteredByRadius is the arraylist of all parking lots that are in the inputted
    // radius. Get that list and sort by price in your interactor.

// Incorporate the time in a way where it records what time the user searches it,
// if there is info about parking time available during that specific time, output it,
// if not, use half hour rate.