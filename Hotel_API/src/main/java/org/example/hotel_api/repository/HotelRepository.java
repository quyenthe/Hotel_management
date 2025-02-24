package org.example.hotel_api.repository;

import org.example.hotel_api.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, String> {
    Optional<List<Hotel>> findByLocation(String location);
    Optional<Hotel> findByName(String name);
}
