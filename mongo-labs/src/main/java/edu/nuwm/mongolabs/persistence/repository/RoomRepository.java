package edu.nuwm.mongolabs.persistence.repository;

import edu.nuwm.mongolabs.persistence.entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
    Room findByRoomType(String RoomType);
}