package org.example.hotel_api.service;

import org.example.hotel_api.model.Hotel;
import org.example.hotel_api.model.Room;

import java.util.List;

public interface HotelService {
    public Hotel addHotel(Hotel hotel);
    public Hotel getHotelById(String hotelId);
    public List<Hotel> getAllHotels();
    public Hotel deleteHotelById(String hotelId);
    public Room addRoom(Room room);
    public Room getRoomById(String roomId);
    public List<Room> getAllAvailableRoom(String hotelId);
    public List<Room> getAllBookedRoom(String hotelId);
    public List<Room> getAllRooms(String hotelId);
    public Room deleteRoomById(String roomId);
    public List<Hotel> getHotelByLocation(String location);
    public Hotel getHotelByName(String name);
}
