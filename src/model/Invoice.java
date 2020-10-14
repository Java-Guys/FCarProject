package model;

import java.util.Date;
import java.util.List;

/**
 * @author Omar Alkashef
 * Creation Date : 10-10-2020
 * @version 1
 */

public class Invoice {

    private static int invoiceNoCounter = 0;
    private int invoiceNo;
    private Date invoiceDate;
    private List<Payment> payments;

    /**
     * @param invoiceDate
     * @param payments
     */
    public Invoice(Date invoiceDate, List<Payment> payments) {
        this.invoiceNo = ++invoiceNoCounter;
        this.invoiceDate = invoiceDate;
        this.payments = payments;
    }

    public Invoice() {
        this.invoiceNo = ++invoiceNoCounter;
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
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * @param invoiceDate
     */
    public void setInvoiceDate(Date invoiceDate) {
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
                return "Updated payment successfully";
            }

        }
        return "Couldn't update the payment";
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
        return "Couldn't delete the payment";
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
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getPaymentId() == paymentId) {
                return payments.get(i);
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

}
