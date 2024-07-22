package use_case_tests;

import org.junit.jupiter.api.Test;
import use_case.FilterByProximity.FilterByProximityInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * FilterByProximityInputData testing class
 */
public class FilterByProximityInputDataTest {
    private FilterByProximityInputData filterByProximityInputData;

    /**
     * Test that FilterByRadiusInputData is appropriately instantiated and fetches the appropriate data
     */
    @Test
    public void FilterByRadiusInputDataCreationTest() {
        String address = "20 Charles Street East";
        filterByProximityInputData = new FilterByProximityInputData(address);
        assertEquals(address, filterByProximityInputData.getAddress());
    }

}
