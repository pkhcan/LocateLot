package use_case.FilterByType;

public class FilterByTypeData {

    final String type;

    public FilterByTypeData(String type) {
        this.type = type;
    }

    /**
     * @return the type of the parking lot, surface or garage
     */
    public String getType(){ return type; }
}
