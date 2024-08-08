package entity;

import data_access.ParkingLotDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PriceFilter {
    List<ParkingLot> sortedByPrice;

    public PriceFilter() {
        this.sortedByPrice = new ArrayList<ParkingLot>();
    }


    public List<ParkingLot> sort(List<ParkingLot> parkingLots, int hour) throws IOException {
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        int n = parkingLots.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                String price11 = parkingLots.get(j).getPrice(parkingLots.get(j), hour);
                String price22 = parkingLots.get(j+1).getPrice(parkingLots.get(j + 1), hour);
                double price1 = Double.parseDouble(price11.replace("$", ""));
                double price2 = Double.parseDouble(price22.replace("$", ""));

                if (price1 > price2) {
                    ParkingLot temp = parkingLots.get(j);
                    parkingLots.set(j, parkingLots.get(j + 1));
                    parkingLots.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }

        return parkingLots;
    }
}