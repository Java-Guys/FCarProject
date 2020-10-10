package model;

import java.time.LocalDate;

/**
 * @author Omar Alkashef
 * Creation Date : 10-10-2020
 * @version 1
 */

public class Rental {
    private int rentalNo;
    private Customer customer;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private double deposit;
    private Invoice invoice;


    public Rental() {

    }

    /**
     * @param rentalNo
     * @param customer
     * @param car
     * @param startDate
     * @param endDate
     * @param deposit
     * @param invoice
     */
    public Rental(int rentalNo,
               Customer customer,
               Car car,
               LocalDate startDate,
               LocalDate endDate,
               double deposit,
               Invoice invoice) {

        this.rentalNo = rentalNo;
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
        this.invoice = invoice;
    }

    /**
     * @return rentalNo
     */
    public int getRentalNo() {
        return rentalNo;
    }

    /**
     * @param rentalNo
     */
    public void setRentalNo(int rentalNo) {
        this.rentalNo = rentalNo;
    }

    /**
     * @return customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return car
     */
    public Car getCar() {
        return car;
    }

    /**
     * @param car
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * @return startDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * @return endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * @return deposit
     */
    public double getDeposit() {
        return deposit;
    }

    /**
     * @param deposit
     */
    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    /**
     * @return invoice
     */
    public Invoice getInvoice() {
        return invoice;
    }

    /**
     * @param invoice
     */
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
