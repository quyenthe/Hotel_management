package org.example.hotel_api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;
    @OneToMany
    private List<Room> rooms;
    @OneToMany
    private List<Booking> booking;
}
