package entity;

public class Review {
    /**
     * each type is easeOfEntry ("Entry") or easeOfFinding ("Find")
     */

    private final int value;
    private final String type;

    /**
     *
     * @param value
     * @param type
     */


    Review(int value, String type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return this.value;
    }

    public String getType() {
        return this.type;
    }
}
