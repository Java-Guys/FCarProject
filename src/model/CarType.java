package model;
/**
 *
 * @author Mahmoud Shreif
 * Date: 10/10/2020
 * Version 1
 *
 */
public enum CarType {
    SEDAN("sedan"),
    SUV("suv"),
    VAN("van"),
    TRUCK("truck");

    private String label;

    private CarType(String label){
        this.label = label;
    }
    public String toString(){
        return label;
    }


}
