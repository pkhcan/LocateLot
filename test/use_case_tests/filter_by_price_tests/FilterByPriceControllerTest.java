package use_case_tests.filter_by_price_tests;

import com.google.maps.errors.ApiException;
import interface_adapter.FilterByPriceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import use_case.FilterByPrice.FilterByPriceInputBoundary;
import use_case.FilterByPrice.FilterByPriceInputData;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * FilterByPriceController testing class
 */
class FilterByPriceControllerTest {
    private FilterByPriceControllerTest.MockFBPInputBoundary mockInteractor;
    private FilterByPriceController controller;

    static class MockFBPInputBoundary implements FilterByPriceInputBoundary {
        private boolean executed = false;
        private FilterByPriceInputData inputData;

        @Override
        public void execute(FilterByPriceInputData inputData){
            this.executed = true;
            this.inputData = inputData;
        }

        public boolean isExecuted() {
            return executed;
        }

        public FilterByPriceInputData getInputData() {
            return inputData;
        }
    }

    @BeforeEach
    void setUp() {
        mockInteractor = new FilterByPriceControllerTest.MockFBPInputBoundary();
        controller = new FilterByPriceController(mockInteractor);
    }

    /**
     * Test that the Price controller has successfully created an input data object with the correct information.
     * @throws IOException
     * @throws InterruptedException
     * @throws ApiException
     */
    @Test
    void testHandlePriceFiltering() throws IOException, InterruptedException, ApiException {
        String address = "123 Main St, Anytown, USA";
        int time = 14;
        FilterByPriceInputData expectedInputData = new FilterByPriceInputData(address, time);

        controller.handlePriceFiltering(address, time);

        assertTrue(mockInteractor.isExecuted(), "Interactor should have been executed");
        assertEquals(expectedInputData.getAddress(), mockInteractor.getInputData().getAddress(), "Input data should match the expected address");
    }
}

