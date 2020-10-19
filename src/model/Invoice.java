package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Omar Alkashef
 * @author M-Hamdy-M
 * Creation Date : 10-10-2020
 * @version 1
 */

public class Invoice {

    private static int invoiceNoCounter = 0;
    private int invoiceNo;
    private LocalDate invoiceDate;
    private List<Payment> payments;

    public Invoice() {
        this.invoiceNo = ++invoiceNoCounter;
        payments = new ArrayList<>();
    }

    /**
     * @param invoiceDate
     * @param payments
     */
    public Invoice(LocalDate invoiceDate, List<Payment> payments) {
        this.invoiceNo = ++invoiceNoCounter;
        this.invoiceDate = invoiceDate;
        this.payments = payments;
    }


    /**
     * @return invoiceNo
     */
    public int getInvoiceNo() {
        return invoiceNo;
    }


    /**
     * @return invoiceDate
     */
    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * @param invoiceDate
     */
    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     * @return payments
     */
    public List<Payment> getPayments() {
        return payments;
    }

    /**
     * @param payments
     */
    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    /**
     * @param payment
     * @return message
     */
    public String modifyPayment(Payment payment) {
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getPaymentId() == payment.getPaymentId()) {
                payments.set(i, payment);
                return "updated payment successfully";
            }
        }
        return "couldn't find the provided payment";
    }

    /**
     * @param paymentId
     * @return message
     */
    public String deletePayment(int paymentId) {

        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getPaymentId() == paymentId) {
                payments.remove(i);
                return "Delete payment successfully";
            }
        }
        return "Couldn't find a payment with this Id";

    }

    /**
     * @param payment
     * @return message
     */
    public String addPayment(Payment payment) {
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getPaymentId() == payment.getPaymentId()) {

                return "This payment is already added";
            }
        }
        payments.add(payment);
        return "Add payment successfully";
    }

    /**
     * @param paymentId
     * @return payment
     */
    public Payment getPayment(int paymentId) {
        for (Payment payment : payments) {
            if (payment.getPaymentId() == paymentId) {
                return payment;
            }
        }
        return null;
    }

    /**
     * @return Total Payment;
     */
    public double calculateTotalPayment() {
        double total = 0.0;
        for (int i = 0; i < payments.size(); i++) {
            total += payments.get(i).getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNo=" + invoiceNo +
                ", invoiceDate=" + invoiceDate +
                ", payments=" + payments +
                '}';
    }
}
