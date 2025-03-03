package org.example.hotel_api.controller;

import org.example.hotel_api.model.Hotel;
import org.example.hotel_api.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @PostMapping("/addHotel")
    ResponseEntity<?> addHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.addHotel(hotel));
    }
    @PostMapping("/getAllHotel")
    ResponseEntity<?> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }
    @PostMapping("/getHotelById")
    ResponseEntity<?> getHotelById(@RequestParam String hotelId) {
        return ResponseEntity.ok(hotelService.getHotelById(hotelId));
    }

    @PostMapping("/deleteHotel")
    ResponseEntity<?> deleteHotel(@RequestParam String hotelId) {
        return ResponseEntity.ok(hotelService.deleteHotelById(hotelId));
    }
    @GetMapping("/getHotelByLocation/{location}")
    ResponseEntity<?> getHotelByLocation(@PathVariable String location) {
        return ResponseEntity.ok(hotelService.getHotelByLocation(location));
    }
    @GetMapping("/getHotelByName/{name}")
    ResponseEntity<?> getHotelByName(@PathVariable String name) {
        return ResponseEntity.ok(hotelService.getHotelByName(name));
    }
}
