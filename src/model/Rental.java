package model;

import java.time.LocalDate;

/**
 * @author Omar Alkashef
 * @author M-Hamdy-M
 * Creation Date : 10-10-2020
 * @version 1
 */

public class Rental {
    private static int rentalNoCounter;
    private int rentalNo;
    private Customer customer;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private double deposit;
    private Invoice invoice;


    public Rental() {
        this.rentalNo = ++rentalNoCounter;
    }

    /**
     * @param customer
     * @param car
     * @param startDate
     * @param endDate
     * @param deposit
     * @param invoice
     */
    public Rental(
            Customer customer,
            Car car,
            LocalDate startDate,
            LocalDate endDate,
            double deposit,
            Invoice invoice) {

        this.rentalNo = ++rentalNoCounter;
        setCustomer(customer);
        setCar(car);
        setStartDate(startDate);
        setEndDate(endDate);
        setDeposit(deposit);
        setInvoice(invoice);
    }


    /**
     * @return rentalNo
     */
    public int getRentalNo() {
        return rentalNo;
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
        if (getStartDate() == null) {
            System.out.println("Please initialize start date first");
            return;
        }
        if (endDate.isBefore(startDate)){
            System.out.println("end date can't be before start date!");
            return;
        }
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
        if (deposit < 0){
            System.out.println("deposit can't be negative! ");
            return;
        }
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

    @Override
    public String toString() {
        return "Rental{" +
                "rentalNo=" + rentalNo +
                ", customer name=" + customer.getName() +
                ", car plate no=" + car.getPlateNo() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", deposit=" + deposit +
                ", invoice=" + invoice +
                '}';
    }
}
