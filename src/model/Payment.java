package model;

import java.time.LocalDate;

/**
 * @author Omar Alkashef
 * @author M-Hamdy-M
 * Creation Date : 10-10-2020
 * @version 1
 */

public class Payment {

    private static int idCounter = 0;
    private String paymentDescription;
    private int paymentId;
    private double price;
    private LocalDate paymentDate;

    public Payment() {
        this.paymentId = ++idCounter;
    }

    /**
     * @param paymentDescription
     * @param price
     * @param paymentDate
     */
    public Payment(String paymentDescription, double price, LocalDate paymentDate) {
        this.paymentDescription = paymentDescription;
        this.paymentId = ++idCounter;
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
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    /**
     * @param paymentDate
     */
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentDescription='" + paymentDescription + '\'' +
                ", paymentId=" + paymentId +
                ", price=" + price +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
