package model;

import java.util.Date;

/**
 * @author Omar Alkashef
 * Creation Date : 10-10-2020
 * @version 1
 */

public class Payment {

    private static int idCounter = 0;
    private int paymentId;
    private String paymentDescription;
    private double price;
    private Date paymentDate;

    /**
     * @param paymentDescription
     * @param price
     * @param paymentDate
     */
    public Payment(String paymentDescription, double price, Date paymentDate) {
        this.paymentId = ++idCounter;
        this.paymentDescription = paymentDescription;
        this.price = price;
        this.paymentDate = paymentDate;
    }

    public Payment() {
        this.paymentId = ++idCounter;
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
