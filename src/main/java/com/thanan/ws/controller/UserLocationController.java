package com.thanan.ws.controller;

import com.thanan.ws.exception.UserLocationNotFoundException;
import com.thanan.ws.model.request.UserLocationRequest;
import com.thanan.ws.model.response.UserLocationResponse;
import com.thanan.ws.service.UserLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserLocationController {

    private UserLocationService userLocationService;

    @Autowired
    public void setUserLocationService(UserLocationService userLocationService){
        this.userLocationService = userLocationService;
    }

    @PostMapping("/{user_id}/locations")
    public UserLocationResponse addLocation(@PathVariable int user_id, @RequestBody UserLocationRequest userLocationRequest) throws Exception {
        try {
            userLocationRequest.setUserId(user_id);
            return userLocationService.addUserLocation(userLocationRequest);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @GetMapping("/{user_id}/locations")
    public List<UserLocationResponse>  getLocationHistory(@PathVariable int user_id, @RequestParam(required = false) Integer limit) throws Exception {
        try {
            PageRequest pageRequest = limit != null ? PageRequest.of(0, limit) : PageRequest.of(0, 1);
            List<UserLocationResponse> historicalLocations = userLocationService.findUserLocations(user_id, pageRequest);

            if (historicalLocations.isEmpty()) {
                throw new UserLocationNotFoundException();
            }

            return historicalLocations;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @MessageMapping("/{user_id}/user-locations")
    @SendTo("/topic/{user_id}/user-locations")
    public UserLocationResponse sendUserLocation(@DestinationVariable Integer user_id,
                                                 @Payload UserLocationRequest userLocationRequest) throws Exception {
        userLocationService.addUserLocation(userLocationRequest);
        return userLocationService.getCurrentLocation(user_id);
    }
}