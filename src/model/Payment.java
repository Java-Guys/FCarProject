package model;

import java.util.Date;

/**
 * @author Omar Alkashef
 * Creation Date : 10-10-2020
 * @version 1
 */

public class Payment {

    private String paymentDescription;
    private int paymentId;
    private double price;
    private Date paymentDate;

    /**
     * @param paymentDescription
     * @param paymentId
     * @param price
     * @param paymentDate
     */
    public Payment(String paymentDescription, int paymentId, double price, Date paymentDate) {
        this.paymentDescription = paymentDescription;
        this.paymentId = paymentId;
        this.price = price;
        this.paymentDate = paymentDate;
    }

    /**
     * @return paymentDescription
     */
    public String getPaymentDescription() {
        return paymentDescription;
    }

    /**
     * @param paymentDescription
     */
    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    /**
     * @return paymentId
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * @param paymentId
     */
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return paymentDate
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * @param paymentDate
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
