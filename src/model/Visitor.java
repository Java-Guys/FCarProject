package model;


import java.time.LocalDate;

/**
 * @author : Ezeldin Ahmed
 * Date    : 10-10-2020
 * version : 1
 */
public class Visitor extends Customer {

    protected int passportNo;
    private LocalDate entryDate;
    private LocalDate visaExpiryDate;

    /**
     * @param customerId
     * @param name
     * @param phone
     * @param address
     * @param nationality
     * @param drivingLicence
     * @param passportNo
     * @param entryDate
     * @param visaExpiryDate
     */
    public Visitor(int customerId,
                   String name,
                   String phone,
                   String address,
                   String nationality,
                   String drivingLicence,
                   int passportNo,
                   LocalDate entryDate,
                   LocalDate visaExpiryDate) {
        super(customerId, name, phone, address, nationality, drivingLicence);
        this.passportNo = passportNo;
        this.entryDate = entryDate;
        this.visaExpiryDate = visaExpiryDate;
    }

    public Visitor() {
    }

    /**
     * @return passportNo
     */

    public int getPassportNo() {
        return passportNo;
    }

    /**
     * @param passportNo
     */
    public void setPassportNo(int passportNo) {
        this.passportNo = passportNo;
    }

    /**
     * @return Date
     */
    public LocalDate getEntryDate() {
        return entryDate;
    }

    /**
     * @param entryDate
     */

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * @return visaExpiryDate
     */
    public LocalDate getVisaExpiryDate() {
        return visaExpiryDate;
    }

    /**
     * @param visaExpiryDate
     */
    public void setVisaExpiryDate(LocalDate visaExpiryDate) {
        this.visaExpiryDate = visaExpiryDate;
    }

    @Override
    public String toString() {
        return super.toString() + entryDate + " | " + visaExpiryDate + " | " + passportNo;
    }
}
