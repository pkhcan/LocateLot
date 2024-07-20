package use_case.FilterByPrice;

import data_access.ParkingLotDAO;

import java.time.LocalTime;
import java.util.ArrayList;
import entity.ParkingLot;

public class FilterByPriceInputData {

    final float min;
    final float max;
    final LocalTime time;
    final int radius;
    final ParkingLotDAO parkingLotDAO;

    public FilterByPriceInputData(float min, float max, LocalTime time, int radius, ParkingLotDAO parkingLotDAO) {
        this.min = min;
        this.max = max;
        this.time = time;
        this.radius = radius;
        this.parkingLotDAO = parkingLotDAO;
    }

    /**
     * @return an array with 2 floats, the minimum and the maximum of the price range
     */
    public float[] getPriceRange(){return new float[]{min, max};};


    /**
     *
     * @return the time of the desired parking lot to get the price range at
     */
    public LocalTime getTime() {return time;};
}

