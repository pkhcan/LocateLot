package data_access;
import entity.ParkingLot;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

// thinking of making this an abstract class extended by different DAOs

public interface GreenPDAO {

    // List of all ParkingLot objects
    public ArrayList<ParkingLot> getParkingLots();

    // Potentially filter here instead
    public ArrayList<ParkingLot> getParkingLotsPrice(double price);

    public ArrayList<ParkingLot> getParkingLotsRadius(float radius);
    // How can we determine lat long of radius? Do we have live location?


}