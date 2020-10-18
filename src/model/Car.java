package model;

import java.text.DecimalFormat;

/**
 * @author Mahmoud Shreif
 * @author M-Hamdy-M
 * Date: 10/10/2020
 * Version 2
 */
public class Car {
    private String plateNo;
    private String model;
    private boolean isAvailable;
    private CarType type;
    private double dailyRentalRate;

    public Car() {
        this.isAvailable = true;
    }

    public Car(String plateNo, String model, CarType type) {
        this.plateNo = plateNo;
        this.model = model;
        this.isAvailable = true;
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


    //for testing purposes

    @Override
    public String toString() {
        DecimalFormat qr = new DecimalFormat("QR#,##0.00");
        return String.format("|%10s|%20s|%10s|%10s|%20s|", getPlateNo(), getModel(), isAvailable(),getType(), qr.format(getDailyRentalRate()));
    }
}
