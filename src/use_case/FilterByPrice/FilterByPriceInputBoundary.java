package use_case.FilterByPrice;

import data_access.ParkingLotDAO;

public interface FilterByPriceInputBoundary {
    public void sort(FilterByPriceInputData filterByPriceInputData, float radius);

}
