package org.example.hotel_api.service;


import org.example.hotel_api.model.Booking;

import java.util.List;

public interface BookingService {
    public String bookRoom(String hotelId, String userId, Booking booking);
    public Booking getBooking(String hotelId, String userId);
    public List<Booking> getAllBookings();
    public String cancelBooking(String hotelId, String userId);
    public String completeBooking(String bookingId);
}
