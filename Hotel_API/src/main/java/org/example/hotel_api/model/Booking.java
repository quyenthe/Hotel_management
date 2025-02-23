package org.example.hotel_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String bookingId;
    private String userId;
    private String roomId;
    private LocalDate bookingDate;
    private LocalDate checkOutDate;
    private Integer amount;
    private PaymentStatus paymentStatus;
    @ManyToOne
    private Hotel hotel;
    @Transient
    @JsonIgnore
    private User user;

}
