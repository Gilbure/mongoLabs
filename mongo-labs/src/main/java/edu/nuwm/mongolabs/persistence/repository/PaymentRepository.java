package edu.nuwm.mongolabs.persistence.repository;

import edu.nuwm.mongolabs.persistence.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PaymentRepository extends MongoRepository<Payment, String> {
    List<Payment> findByAmount (String Amount);
}
