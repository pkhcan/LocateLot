package entity;

public class Review {

    private final int value;
    private final String type;


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
