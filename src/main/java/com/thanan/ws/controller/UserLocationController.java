package com.thanan.ws.controller;

import com.thanan.ws.entity.UserLocation;
import com.thanan.ws.exception.UserLocationNotFoundException;
import com.thanan.ws.model.request.UserLocationRequest;
import com.thanan.ws.model.response.UserLocationResponse;
import com.thanan.ws.service.UserLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
}