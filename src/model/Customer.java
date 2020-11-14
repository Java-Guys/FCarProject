package model;
/**
 * @author : Ezeldin Ahmed
 * Date    : 10-10-2020
 * version : 1
 */
public class Customer {
    private int customerId;
    private String name;
    private String phone;
    private String address;
    private String nationality;
    private String drivingLicence;

    public Customer() {
    }

    /**
     * @param customerId
     * @param name
     * @param phone
     * @param address
     * @param nationality
     * @param drivingLicence
     */
     public  Customer(int customerId,
                    String name,
                    String phone,
                    String address,
                    String nationality,
                    String drivingLicence) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.nationality = nationality;
        this.drivingLicence = drivingLicence;
    }

    /**
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return getName
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return drivingLicence
     */
    public String getDrivingLicence() {
        return drivingLicence;
    }

    /**
     * @param drivingLicence
     */
    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence;
    }

    //for testing purposes
    @Override
    public String toString() {
        return String.format("|%10s|%20s|%15s|%25s|%15s|%20s|",
                getCustomerId(),
                getName(),
                getPhone(),
                getAddress(),
                getNationality(),
                getDrivingLicence());
    }
}


