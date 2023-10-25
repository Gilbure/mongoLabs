package edu.nuwm.mongolabs.service;

import edu.nuwm.mongolabs.persistence.entity.Booking;
import edu.nuwm.mongolabs.persistence.entity.Room;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final MongoTemplate mongoTemplate;

    public SearchService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Room> findRoomsByBookingId(final String bookingId) {
        final Criteria criteria = Criteria.where("id").is(bookingId);
        final Query query = Query.query(criteria);
        Booking booking = mongoTemplate.findOne(query, Booking.class);

        if (booking != null) {
            final String roomId = booking.getRoomId();
            final Criteria roomCriteria = Criteria.where("id").is(roomId);
            final Query roomQuery = Query.query(roomCriteria);
            return mongoTemplate.find(roomQuery, Room.class);
        }

        return null;
    }
}
