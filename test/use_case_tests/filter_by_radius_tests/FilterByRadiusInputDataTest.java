package use_case_tests.filter_by_radius_tests;

import org.junit.jupiter.api.Test;
import use_case.FilterByRadius.FilterByRadiusInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test case for filter by radius input data
 */

public class FilterByRadiusInputDataTest {
    FilterByRadiusInputData filterByRadiusInputData;

    /**
     * Test that FilterByRadiusInputData can create and retrieveinput data with radius and address input
     */
    @Test
    public void FilterByRadiusInputDataCreationAndRetrievalTest() {
        double radius = 3.0;
        String address = "20 Charles Street East";
        filterByRadiusInputData = new FilterByRadiusInputData(radius, address);
        assertEquals(radius, filterByRadiusInputData.getRadius());
        assertEquals(address, filterByRadiusInputData.getAddress());
    }

}
