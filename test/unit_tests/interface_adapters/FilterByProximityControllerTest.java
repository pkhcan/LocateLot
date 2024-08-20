package unit_tests.interface_adapters;

import interface_adapter.FilterByProximityController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.FilterByProximity.FilterByProximityInputData;
import use_case.FilterByProximity.FilterByProximityInteractor;


import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

/**
 * Filter by proximity controller test class
 */
public class FilterByProximityControllerTest {

    private FilterByProximityController filterByProximityController;
    private FilterByProximityInteractor mockFilterByProximityInteractor;

    /**
     * Set up mock dependencies and instance of controller object
     */
    @BeforeEach
    public void setUp() {
        // Mock the interactor
        mockFilterByProximityInteractor = Mockito.mock(FilterByProximityInteractor.class);
        filterByProximityController = new FilterByProximityController(mockFilterByProximityInteractor);
    }

    /**
     * Test controller execute method
     * @throws Exception
     */
    @Test
    public void testExecute() throws Exception {
        String expectedAddress = "20 Charles Street East, Toronto, ON, Canada";

        // Execute the controller method
        filterByProximityController.execute(expectedAddress);

        // Capture the argument passed to the interactor's execute method
        ArgumentCaptor<FilterByProximityInputData> captor = ArgumentCaptor.forClass(FilterByProximityInputData.class);
        verify(mockFilterByProximityInteractor).execute(captor.capture());

        // Assert that the captured argument has the expected properties
        FilterByProximityInputData actualInputData = captor.getValue();
        assertEquals(expectedAddress, actualInputData.getAddress());
    }

}
