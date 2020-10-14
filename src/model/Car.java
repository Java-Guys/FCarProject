package model;

/**
 *
 * @author Mahmoud Shreif
 * Date: 10/10/2020
 * Version 1
 *
 */
class Car {
    private String plateNo;
    private String model;
    private boolean isAvailable;
    private CarType type;

    public Car(String plateNo, String model, boolean isAvailable, CarType type) {
        this.plateNo = plateNo;
        this.model = model;
        this.isAvailable = isAvailable;
        this.type = type;

    }

    public Car() {

    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

}