package use_case_tests;

import com.google.maps.errors.ApiException;
import interface_adapter.EOEController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.FilterByEOE.EOEInputBoundary;
import use_case.FilterByEOE.EOEInputData;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EOEControllerTest {

    private MockEOEInputBoundary mockInteractor;
    private EOEController controller;

    // A simple mock implementation of EOEInputBoundary
    static class MockEOEInputBoundary implements EOEInputBoundary {
        private boolean executed = false;
        private EOEInputData inputData;

        @Override
        public void execute(EOEInputData inputData){
            this.executed = true;
            this.inputData = inputData;
        }

        public boolean isExecuted() {
            return executed;
        }

        public EOEInputData getInputData() {
            return inputData;
        }
    }

    @BeforeEach
    void setUp() {
        mockInteractor = new MockEOEInputBoundary();
        controller = new EOEController(mockInteractor);
    }

    @Test
    void testHandleEOE() throws IOException, InterruptedException, ApiException {
        // Arrange
        String address = "123 Main St, Anytown, USA";
        EOEInputData expectedInputData = new EOEInputData(address);

        // Act
        controller.handleEOE(address);

        // Assert
        assertTrue(mockInteractor.isExecuted(), "Interactor should have been executed");
        assertEquals(expectedInputData.getAddress(), mockInteractor.getInputData().getAddress(), "Input data should match the expected address");
    }
}
