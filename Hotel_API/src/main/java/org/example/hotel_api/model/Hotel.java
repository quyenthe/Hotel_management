package org.example.hotel_api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Hotel {
    @Id

    private String id;
    @Column(unique=true)
    private String name;
    private String location;
    @OneToMany
    private List<Room> rooms;
    @OneToMany
    private List<Booking> booking;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
    }
}
