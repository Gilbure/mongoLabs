package edu.nuwm.mongolabs.persistence.repository;

import edu.nuwm.mongolabs.persistence.entity.Service;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceRepository extends MongoRepository<Service, String> {
    Service findByServiceName(String serviceName);
}