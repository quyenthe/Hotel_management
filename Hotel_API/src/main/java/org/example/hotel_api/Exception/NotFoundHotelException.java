package org.example.hotel_api.Exception;

public class NotFoundHotelException extends RuntimeException {
    public NotFoundHotelException(String message) {
        super(message);
    }
}
