package entity;

import entity.ParkingLot;

public interface Filter {

    default void filter(ParkingLot[] parkingResults) {

    }
}
