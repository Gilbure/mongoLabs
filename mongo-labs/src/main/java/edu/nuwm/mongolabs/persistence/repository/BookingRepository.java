package edu.nuwm.mongolabs.persistence.repository;

import edu.nuwm.mongolabs.persistence.entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

public interface BookingRepository extends MongoRepository<Booking, Date> {
    Booking findByCheckInDate(Date CheckInDate);
}