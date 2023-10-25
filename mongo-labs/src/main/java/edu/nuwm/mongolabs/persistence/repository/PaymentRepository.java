package edu.nuwm.mongolabs.persistence.repository;

import edu.nuwm.mongolabs.persistence.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

public interface PaymentRepository extends MongoRepository<Payment, Date> {
    Payment findByPaymentDate(Date PaymentDate);
}