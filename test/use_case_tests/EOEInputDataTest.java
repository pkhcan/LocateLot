package use_case_tests;

import org.junit.jupiter.api.Test;
import use_case.FilterByEOE.EOEInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EOEInputDataTest {
    @Test
    void testEOEInputDataCreation() {
        String address = "123 Test St, City, Country";
        EOEInputData data = new EOEInputData(address);
        assertEquals(address, data.getAddress());
    }
}
