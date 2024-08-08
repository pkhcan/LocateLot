package entity;

import data_access.ParkingLotDAO;

import java.util.ArrayList;
import java.util.List;

public class PriceFilter {
    List<ParkingLot> sortedByPrice;

    public PriceFilter() {
        this.sortedByPrice = new ArrayList<ParkingLot>();
    }


    public void bubbleSort(List<ParkingLot> parkingLots, int hour) {
        int n = parkingLots.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                double price1 = Double.parseDouble(ParkingLotDAO.getParkingLotPrice(parkingLots.get(j), hour));
                double price2 = Double.parseDouble(ParkingLotDAO.getParkingLotPrice(parkingLots.get(j + 1), hour));
                if (price1 > price2) {
                    ParkingLot temp = parkingLots.get(j);
                    parkingLots.set(j, parkingLots.get(j + 1));
                    parkingLots.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}