package unit_tests.interface_adapters;

import app.gui.GUI;
import interface_adapter.FilterByRadiusController;
import interface_adapter.FilterByRadiusViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import views.FilterByRadiusView;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * A test class for the filter by radius view class
 */
public class FilterByRadiusViewTest {

    private FilterByRadiusController mockController;
    private FilterByRadiusViewModel mockViewModel;
    private GUI mockGui;
    private JTextField textFieldAddress;
    private FilterByRadiusView filterByRadiusView;

    /**
     * Set up mock dependencies
     */
    @BeforeEach
    public void setUp() {
        mockController = mock(FilterByRadiusController.class);
        mockViewModel = mock(FilterByRadiusViewModel.class);
        mockGui = mock(GUI.class);
        textFieldAddress = new JTextField("20 Charles Street East");

        filterByRadiusView = new FilterByRadiusView(mockController, mockViewModel, mockGui, textFieldAddress);
    }

    /**
     * test that property changes result in updated parking lots
     */
    @Test
    public void testPropertyChangeUpdatesParkingLots() {
        // Given
        PropertyChangeEvent event = new PropertyChangeEvent(mockViewModel, "parkingLots", null, null);

        // When
        filterByRadiusView.propertyChange(event);

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
        filterByRadiusView.propertyChange(event);

        // Then
        verify(mockGui, never()).updateParkingLotList(mockViewModel.getParkingLots());
        verify(mockViewModel).getErrorMessage();
    }

    /**
     * test that the filter button will function as it should
     * @throws Exception
     */
    @Test
    public void testFilterButtonActionPerformed() throws Exception {
        // Given
        JTextField textFieldRadius = getPrivateTextFieldRadius();
        textFieldRadius.setText("5.0");

        // Find the button by its text or position in the panel
        JButton button = findButtonByText(filterByRadiusView, "Filter");

        // When
        button.doClick();

        // Then
        ArgumentCaptor<Double> radiusCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<String> addressCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockController).execute(radiusCaptor.capture(), addressCaptor.capture());

        assertEquals(5.0, radiusCaptor.getValue());
        assertEquals("20 Charles Street East", addressCaptor.getValue());
    }

    /**
     * Helper method to access text field radius
     * @return JTextField
     */
    private JTextField getPrivateTextFieldRadius() {
        return (JTextField) filterByRadiusView.getComponent(1);
    }

    /**
     *  Helper method to find the button by its text
     * @return JButton
     */
    private JButton findButtonByText(JPanel panel, String text) {
        for (int i = 0; i < panel.getComponentCount(); i++) {
            if (panel.getComponent(i) instanceof JButton) {
                JButton button = (JButton) panel.getComponent(i);
                if (button.getText().equals(text)) {
                    return button;
                }
            }
        }
        throw new IllegalArgumentException("Button with text '" + text + "' not found");
    }
}
