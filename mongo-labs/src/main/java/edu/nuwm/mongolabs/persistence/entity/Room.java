package edu.nuwm.mongolabs.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "room")
public class Room {
    @Id
    private String id;
    private String RoomNumber;
    private String RoomType;
    private String PricePerNight;
    private String Capacity;
    private String Description;
    private String Status;

    public Room(String RoomNumber, String RoomType, String PricePerNight, String Capacity, String Description, String Status) {
        this.RoomNumber = RoomNumber;
        this.RoomType = RoomType;
        this.PricePerNight = PricePerNight;
        this.Capacity = Capacity;
        this.Description = Description;
        this.Status = Status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.RoomNumber = roomNumber;
    }

    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String roomType) {
        this.RoomType = roomType;
    }

    public String getPricePerNight() {
        return PricePerNight;
    }

    public void setPricePerNight(String pricePerNight) {
        this.PricePerNight = pricePerNight;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        this.Capacity = capacity;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }
}
