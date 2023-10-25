package edu.nuwm.mongolabs.persistence.repository;

import edu.nuwm.mongolabs.persistence.entity.Guest;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface GuestRepository extends MongoRepository<Guest, String> {
}
