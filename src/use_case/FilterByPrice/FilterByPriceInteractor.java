package use_case.FilterByPrice;

import data_access.ParkingLotDAO;
import entity.ParkingLot;

import java.util.List;

public class FilterByPriceInteractor implements FilterByPriceInputBoundary {
    private final FilterByPriceOutputBoundary outputBoundary;
    private final ParkingLotDAO parkingLotDAO;

    public FilterByPriceInteractor(FilterByPriceOutputBoundary outputBoundary, ParkingLotDAO parkingLotDAO) {
        this.outputBoundary = outputBoundary;
        this.parkingLotDAO = parkingLotDAO;
    }

    @Override
    public void execute(FilterByPriceInputData inputData) {
        List<ParkingLot> parkingLots = parkingLotDAO.getParkingLots();
        int userHour = inputData.time.getHour();

        bubbleSort(parkingLots, userHour);

        FilterByPriceOutputData outputData = new FilterByPriceOutputData(parkingLots);
        outputBoundary.prepareSuccessView(outputData);
    }

    private void bubbleSort(List<ParkingLot> parkingLots, int hour) {
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