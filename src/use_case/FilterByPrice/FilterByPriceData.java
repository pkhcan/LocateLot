package use_case.FilterByPrice;

import java.time.LocalTime;

public class FilterByPriceData {
    final float min;
    final float max;
    final LocalTime time;

    public FilterByPriceData(float min, float max, LocalTime time) {
        this.min = min;
        this.max = max;
        this.time = time;
    }

    /**
     * @return an array with 2 floats, the minimum and the maximum of the price range
     */
    public float[] getPriceRange(){return new float[]{min, max};};


    /**
     *
     * @return the time of the desired time to get the price range at
     */
    public LocalTime getTime() {return time;};
}
