package edu.nuwm.mongolabs.persistence.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "payment")
public class Payment {
    private String Amount;
    private String PaymentDate;
    private String PaymentMethod;

    public Payment(String amount, String paymentDate, String paymentMethod) {
        this.Amount = amount;
        this.PaymentDate = paymentDate;
        this.PaymentMethod = paymentMethod;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        return new EqualsBuilder()
                .append(Amount, payment.Amount)
                .append(PaymentDate, payment.PaymentDate)
                .append(PaymentMethod, payment.PaymentMethod)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(Amount)
                .append(PaymentDate)
                .append(PaymentMethod)
                .toHashCode();
    }
}
