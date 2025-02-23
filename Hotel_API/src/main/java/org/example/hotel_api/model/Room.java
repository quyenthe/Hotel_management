package org.example.hotel_api.model;

import jakarta.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roomType;
    private int roomNumber;
    private int roomPrice;
    private Status status;
    @ManyToOne
    private Hotel hotel;
}
