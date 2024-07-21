package entity;

public class Review {
    /**
     * each type is easeOfEntry ("Entry") or easeOfFinding ("Find")
     */

    private int value;
//    private String type;

    /**
     * @param value
     */


    public Review(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

//    public String getType() {
//        return this.type;
//    }

//    public void setType(String value) {
//        this.type = value;
//    }

}
