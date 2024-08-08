package use_case_tests.filter_by_price_tests;

import org.junit.jupiter.api.Test;
import use_case.FilterByPrice.FilterByPriceInputData;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FilterByPriceInputDataTest {

    FilterByPriceInputData filterByPriceInputData;

    @Test
    void FilterByPriceInputDataCreationAndRetrievalTest() {
        int time = LocalTime.now().getHour();
        String address = "20 Charles Street East";
        filterByPriceInputData = new FilterByPriceInputData(address, time);
        assertEquals(time, filterByPriceInputData.getTime());
        assertEquals(address, filterByPriceInputData.getAddress());

    }
}