package com.thanan.ws.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserLocationResponse {
    private int id;
    private int userId;
    private double latitude;
    private double longitude;
    private LocalDateTime timeStamp;
}