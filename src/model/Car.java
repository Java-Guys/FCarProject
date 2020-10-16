package model;

/**
 * @author Mahmoud Shreif
 * @author M-Hamdy-M
 * Date: 10/10/2020
 * Version 2
 */
class Car {
    private String plateNo;
    private String model;
    private boolean isAvailable;
    private CarType type;
    private double dailyRentalRate;

    public Car() {

    }

    public Car(String plateNo, String model, boolean isAvailable, CarType type) {
        this.plateNo = plateNo;
        this.model = model;
        this.isAvailable = isAvailable;
        this.type = type;

    }

    public Car(String plateNo, String model, boolean isAvailable, CarType type, double dailyRentalRate, double monthlyRentalRate) {
        this.plateNo = plateNo;
        this.model = model;
        this.isAvailable = isAvailable;
        this.type = type;
        this.dailyRentalRate = dailyRentalRate;
    }

    public double getDailyRentalRate() {
        return dailyRentalRate;
    }

    public void setDailyRentalRate(double dailyRentalRate) {
        this.dailyRentalRate = dailyRentalRate;
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
