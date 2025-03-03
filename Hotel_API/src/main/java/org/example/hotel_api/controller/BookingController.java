package org.example.hotel_api.controller;

import org.example.hotel_api.model.Booking;
import org.example.hotel_api.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @PostMapping("/addBooking/{hotelId}/{userId}")
    ResponseEntity<String> addBooking(@PathVariable String hotelId, @PathVariable int userId, @RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.bookRoom(hotelId,userId,booking));
    }
    @GetMapping("/getBooking/{bookingId}")
    ResponseEntity<Booking> getBooking(@PathVariable String bookingId) {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }
    @GetMapping("/getAllBooking")
    public ResponseEntity<List<Booking>> getAllBooking() {
        return ResponseEntity.ok(bookingService.getAllBookings());

    }
    @GetMapping("/cancelBooking/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable String bookingId) {
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }



}
