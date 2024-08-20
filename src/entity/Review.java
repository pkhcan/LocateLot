package entity;

public class Review {
    private int value;

    /**
     * Constructs a review object
     * @param value The value for this review, which is an integer 1-5.
     */
    public Review(int value) {
        this.value = value;
    }

    /**
     * Get the rating for this Review
     * @return
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Update the value for this review
     * @param value the new value, which is an integer 1-5
     */
    public void setValue(int value) {
        this.value = value;
    }

}
