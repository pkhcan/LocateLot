package use_case.FilterByPrice;

import data_access.ParkingLotDAO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import entity.ParkingLot;
import entity.ProximityFilter;

public class FilterByPriceInputData {

    final String address;
    final int time;

    public FilterByPriceInputData(String address, int time) {
        this.address = address;
        this.time = time;
    }

    public int getTime(){return this.time;}

    public String getAddress(){return this.address;}
}

