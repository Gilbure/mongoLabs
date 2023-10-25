package edu.nuwm.mongolabs.persistence.repository;

import edu.nuwm.mongolabs.persistence.entity.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ReservationRepository extends MongoRepository<Reservation, String> {
    Reservation findByStatus (String status);
}