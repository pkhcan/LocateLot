package entity;

import java.util.Arrays;
import java.util.Comparator;

public class EOEFilter implements Filter {

    /**
     * Sort array of ParkingLot objects in place based on entry reviews.
     * Highest reviews at index 0, lowest at the end, with unrated lots at the end.
     *
     * @param parkingResults Array of ParkingLot objects to be sorted
     */

    public void filter(ParkingLot[] parkingResults) {
        Arrays.sort(parkingResults, new Comparator<ParkingLot>() {
            /**
             *
             * @param p1 the first object to be compared.
             * @param p2 the second object to be compared.
             * @return integer denoting comparison, indicating movement to be made by the comparator object
             */
            @Override
            public int compare(ParkingLot p1, ParkingLot p2) {
                String review1 = p1.getEntryReview();
                String review2 = p2.getEntryReview();

                // Handle unrated lots by placing them at the end
                if (review1.equals("No reviews yet") && review2.equals("No reviews yet")) {
                    return 0;
                }
                // if only review 1 is unrated - comparator will move lot 1 after lot 2
                else if (review1.equals("No reviews yet")) {
                    return 1;
                }
                // if only review 2 is unrated - comparator will move lot 2 after lot 1
                else if (review2.equals("No reviews yet")) {
                    return -1;
                } else {
                    // Compare the reviews as floats
                    float reviewValue1 = Float.parseFloat(review1);
                    float reviewValue2 = Float.parseFloat(review2);
                    return Float.compare(reviewValue2, reviewValue1);
                }
            }
        });
    }
}

