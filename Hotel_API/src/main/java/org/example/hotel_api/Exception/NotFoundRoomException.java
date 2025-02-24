package org.example.hotel_api.Exception;

public class NotFoundRoomException extends RuntimeException {
    public NotFoundRoomException(String message) {
        super(message);
    }
}
