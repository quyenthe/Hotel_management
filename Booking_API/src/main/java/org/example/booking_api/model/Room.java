package org.example.booking_api.model;


import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class Room {

    private String id;
    private String roomType;
    private int roomNumber;

    private int roomPrice;

    @ManyToOne
    private Hotel hotel;

}
