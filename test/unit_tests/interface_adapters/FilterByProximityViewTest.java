package unit_tests.interface_adapters;

import app.gui.GUI;
import interface_adapter.FilterByProximityController;
import interface_adapter.FilterByProximityViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.FilterByProximityView;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import static org.mockito.Mockito.*;

/**
 * A test class for the filter by Proximity view class
 */
public class FilterByProximityViewTest {

    private FilterByProximityController mockController;
    private FilterByProximityViewModel mockViewModel;
    private GUI mockGui;
    private JTextField textFieldAddress;
    private FilterByProximityView filterByProximityView;

    /**
     * Set up mock dependencies
     */
    @BeforeEach
    public void setUp() {
        mockController = mock(FilterByProximityController.class);
        mockViewModel = mock(FilterByProximityViewModel.class);
        mockGui = mock(GUI.class);
        textFieldAddress = new JTextField("20 Charles Street East");

        filterByProximityView = new FilterByProximityView(mockController, mockViewModel, mockGui, textFieldAddress);
    }

    /**
     * test that property changes result in updated parking lots
     */
    @Test
    public void testPropertyChangeUpdatesParkingLots() {
        // Given
        PropertyChangeEvent event = new PropertyChangeEvent(mockViewModel, "parkingLots", null, null);

        // When
        filterByProximityView.propertyChange(event);

        // Then
        verify(mockGui).updateParkingLotList(mockViewModel.getParkingLots());
    }


    /**
     * Test that property changes will show an error message accordingly
     */
    @Test
    public void testPropertyChangeShowsErrorMessage() {
        // Given
        String errorMessage = "An error occurred";
        when(mockViewModel.getErrorMessage()).thenReturn(errorMessage);
        PropertyChangeEvent event = new PropertyChangeEvent(mockViewModel, "errorMessage", null, null);

        // When
        filterByProximityView.propertyChange(event);

        // Then
        verify(mockGui, never()).updateParkingLotList(mockViewModel.getParkingLots());
        verify(mockViewModel).getErrorMessage();
    }
}
