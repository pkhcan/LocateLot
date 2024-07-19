package use_case.FilterByPrice;

import data_access.ParkingLotDAO;

public interface FilterByPriceInputBoundary {
    public void sort(ParkingLotDAO parkingLotDAO, int radius);

}
