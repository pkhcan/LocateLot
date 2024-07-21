package use_case_tests;

import org.junit.jupiter.api.Test;
import use_case.FilterByEOE.EOEInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for use_case.FilterByEOE.EOEInputData.
 */
class EOEInputDataTest {
    /**
     * Tests the creation of an EOEInputData object and verifies that
     * the address is correctly set.
     */
    @Test
    void testEOEInputDataCreation() {
        String address = "123 Test St, City, Country";
        EOEInputData data = new EOEInputData(address);
        assertEquals(address, data.getAddress());
    }
}
