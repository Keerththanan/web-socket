package com.thanan.ws.exception;

public class UserLocationNotFoundException extends DataNotFoundException {

    @Override
    public String getMessage() {
        return "UserLocation not found";
    }
}