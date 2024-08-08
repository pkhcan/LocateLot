package use_case.FilterByType;

import data_access.GreenPDAO;
import data_access.ParkingLotDAO;
import entity.ParkingLot;
import entity.TypeFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Filter by type use case interactor. Implements filter by type input boundary.
 */
public class FilterByTypeInteractor implements FilterByTypeInputBoundary {

    public GreenPDAO parkingLotDAO;
    FilterByTypeOutputBoundary filterByTypePresenter;
    public List<ParkingLot> filteredByType;

    /**
     * Constructor method
     * @param filterByTypePresenter
     * @throws IOException
     */
    public FilterByTypeInteractor(FilterByTypeOutputBoundary filterByTypePresenter) throws IOException {
        this.parkingLotDAO = new ParkingLotDAO();
        this.filterByTypePresenter = filterByTypePresenter;
        this.filteredByType = new ArrayList<>();
    }

    /**
     * Passes user input from GUI to filter entity, then retrieves output data and sends to presenter
     * @param filterByTypeInputData contains user input for type
     */
    @Override
    public void execute(FilterByTypeInputData filterByTypeInputData) {
        try {
            String type = filterByTypeInputData.getType(); // type passed to type filter entity

            TypeFilter filter = new TypeFilter(); // new instance of type filter entity
            filteredByType = filter.filter(type, parkingLotDAO.getParkingLots());
            FilterByTypeOutputData filterByTypeOutputData = new FilterByTypeOutputData(filteredByType);
            filterByTypePresenter.prepareSuccessView(filterByTypeOutputData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            filterByTypePresenter.prepareFailView("Filtering issue. Please try again later.");
        }
    }
}
