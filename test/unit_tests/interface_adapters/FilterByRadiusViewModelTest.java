package unit_tests.interface_adapters;

import entity.ParkingLot;
import interface_adapter.FilterByRadiusViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Testing the filter by radius view model
 */

public class FilterByRadiusViewModelTest {

    private FilterByRadiusViewModel viewModel;
    private PropertyChangeListener mockListener;

    /**
     * Set up the view model and mock dependencies (i.e. mock listener)
     */
    @BeforeEach
    public void setUp() {
        viewModel = new FilterByRadiusViewModel();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    /**
     * Test that set parking lots method notifies listeners
     */
    @Test
    public void testSetParkingLotsNotifiesListeners() {
        // Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot("20 Charles Street East");
        ParkingLot parkingLot2 = new ParkingLot("13 Isabella Street");
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        // When
        viewModel.setParkingLots(parkingLots);

        // Then
        assertEquals(parkingLots, viewModel.getParkingLots());

        verify(mockListener, times(1)).propertyChange(argThat(event ->
                event.getPropertyName().equals("parkingLots") && event.getNewValue().equals(parkingLots)
        ));
    }

    /**
     * Test that set error message method notifies listeners
     */
    @Test
    public void testSetErrorMessageNotifiesListeners() {
        // Given
        String errorMessage = "An error occurred";

        // When
        viewModel.setErrorMessage(errorMessage);

        // Then
        assertEquals(errorMessage, viewModel.getErrorMessage());

        verify(mockListener, times(1)).propertyChange(argThat(event ->
                event.getPropertyName().equals("errorMessage") && event.getNewValue().equals(errorMessage)
        ));
    }

    /**
     * test adding a new proeprty change listener
     */
    @Test
    public void testAddPropertyChangeListener() {
        // Given
        PropertyChangeListener listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);

        // When
        viewModel.setErrorMessage("Another error");

        // Then
        verify(listener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }
}
