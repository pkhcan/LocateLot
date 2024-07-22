package use_case.FilterByPrice;

import data_access.ParkingLotDAO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import entity.ParkingLot;
import entity.ProximityFilter;

public class FilterByPriceInputData {

    final ParkingLotDAO radiusSortedList;
    final int time;

    public FilterByPriceInputData(ParkingLotDAO radiusSortedList, int time) {
        this.radiusSortedList = radiusSortedList;
        this.time = time;
    }
}

