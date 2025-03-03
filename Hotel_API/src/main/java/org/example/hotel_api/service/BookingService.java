package org.example.hotel_api.service;


import org.example.hotel_api.model.Booking;

import java.util.List;

public interface BookingService {
    public String bookRoom(String hotelId, int userId, Booking booking);
    public Booking getBookingById(String bookingId);
    public List<Booking> getAllBookings();
    public String cancelBooking(String bookingId);

}
