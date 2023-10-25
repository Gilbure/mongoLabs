package edu.nuwm.mongolabs.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "guest")
public class Guest {
    @Id
    private String id;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Phone;
    private String PaymentInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getPaymentInfo() {
        return PaymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.PaymentInfo = paymentInfo;
    }
}
