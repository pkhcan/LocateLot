package interface_adapter;

import entity.ParkingLot;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class FilterByProximityViewModel {
    private List<ParkingLot> parkingLots;
    private String errorMessage;
    private final PropertyChangeSupport support;

    /**
     * FilterByProximityViewModel monitors property changes and notifies view model with appropriate information.
     */
    public FilterByProximityViewModel() {
        this.support = new PropertyChangeSupport(this);
    }

    /**
     * Notify view of filtered parking lot list
     * @param parkingLots
     */
    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        support.firePropertyChange("parkingLots", null, parkingLots);
    }

    /**
     * Filtered parking lot list getter method for view
     * @return
     */
    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    /**
     * Notifies view of updated error message
     * @param errorMessage
     */

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        support.firePropertyChange("errorMessage", null, errorMessage);
    }

    /**
     * Getter method for error message for view to display
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Listen for property changes
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
