package model;

/**
 * @author : Ezeldin Ahmed
 * Date    : 10-10-2020
 * version : 1
 */
public class  Resident extends Customer {
    private int qatarId;

    public Resident(){

    }

    /**
     * @param customerId
     * @param name
     * @param phone
     * @param address
     * @param nationality
     * @param drivingLicence
     * @param qatarId
     */
    public Resident(int customerId, String name, String phone, String address, String nationality, String drivingLicence, int qatarId) {
        super(customerId, name, phone, address, nationality, drivingLicence);
        this.qatarId = qatarId;
    }

    /**
     * @return qatarId
     */
    public int getQatarId() {
        return qatarId;
    }

    /**
     * @param qatarId
     */
    public void setQatarId(int qatarId) {
        this.qatarId = qatarId;
    }
}
