package unit_tests.interface_adapters;

import interface_adapter.FilterByRadiusController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.FilterByRadius.FilterByRadiusInputData;
import use_case.FilterByRadius.FilterByRadiusInteractor;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

/**
 * Test for filter by radius controller class
 */
public class FilterByRadiusControllerTest {

    private FilterByRadiusController filterByRadiusController;
    private FilterByRadiusInteractor mockFilterByRadiusInteractor;

    /**
     * mock dependencies and create instance of controller
     */
    @BeforeEach
    public void setUp() {
        // Mock the interactor
        mockFilterByRadiusInteractor = Mockito.mock(FilterByRadiusInteractor.class);
        filterByRadiusController = new FilterByRadiusController(mockFilterByRadiusInteractor);
    }

    /**
     * Test that the controller class correctly executes interactor class
     * @throws Exception
     */
    @Test
    public void testExecute() throws Exception {
        double expectedRadius = 5.0;
        String expectedAddress = "20 Charles Street East, Toronto, ON, Canada";

            // Execute the controller method
            filterByRadiusController.execute(expectedRadius, expectedAddress);

            // Capture the argument passed to the interactor's execute method
            ArgumentCaptor<FilterByRadiusInputData> captor = ArgumentCaptor.forClass(FilterByRadiusInputData.class);
            verify(mockFilterByRadiusInteractor).execute(captor.capture());

            // Assert that the captured argument has the expected properties
            FilterByRadiusInputData actualInputData = captor.getValue();
            assertEquals(expectedRadius, actualInputData.getRadius());
            assertEquals(expectedAddress, actualInputData.getAddress());
        }

    }
