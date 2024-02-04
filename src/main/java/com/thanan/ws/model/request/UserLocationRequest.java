package com.thanan.ws.model.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserLocationRequest {
    private int userId;
    private double latitude;
    private double longitude;
    private LocalDateTime timeStamp;
}
