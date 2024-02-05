package com.thanan.ws.service;

import com.thanan.ws.entity.UserLocation;
import com.thanan.ws.mapper.request.UserLocationRequestMapper;
import com.thanan.ws.mapper.response.UserLocationResponseMapper;
import com.thanan.ws.model.request.UserLocationRequest;
import com.thanan.ws.model.response.UserLocationResponse;
import com.thanan.ws.repository.UserLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserLocationService {

    private UserLocationRepository userLocationRepository;
    private UserLocationRequestMapper userLocationRequestMapper;
    private UserLocationResponseMapper userLocationResponseMapper;

    @Autowired
    public void setUserLocationRepository(UserLocationRepository userLocationRepository) {
        this.userLocationRepository = userLocationRepository;
    }

    @Autowired
    public void setUserLocationRequestMapper(UserLocationRequestMapper userLocationRequestMapper) {
        this.userLocationRequestMapper = userLocationRequestMapper;
    }

    @Autowired
    public void setUserLocationResponseMapper(UserLocationResponseMapper userLocationResponseMapper) {
        this.userLocationResponseMapper = userLocationResponseMapper;
    }

    public UserLocationResponse addUserLocation(UserLocationRequest userLocationRequest) throws Exception {
        UserLocation savedUserLocation = new UserLocation();

        try {
            savedUserLocation = userLocationRepository.save(userLocationRequestMapper.userLocationRequestToUserLocation(userLocationRequest));
        } catch (Exception ex) {
            throw new Exception(ex);
        }

        return userLocationResponseMapper.userLocationToUserLocationResponse(savedUserLocation);
    }

    public UserLocationResponse getCurrentLocation(int user_id) throws Exception {
        UserLocation currentLocation = new UserLocation();

        try {
            currentLocation = userLocationRepository.findTopByUserIdOrderByTimeStampDesc(user_id);
        } catch (Exception ex) {
            throw new Exception(ex);
        }

        return userLocationResponseMapper.userLocationToUserLocationResponse(currentLocation);
    }

    public List<UserLocationResponse> findUserLocations(int user_id, PageRequest pageRequest) throws Exception {
        List<UserLocation> historicalLocations = new ArrayList<>();

        try {
            historicalLocations = userLocationRepository.findByUserIdOrderByTimeStampDesc(user_id, pageRequest);
        } catch (Exception ex) {
            throw new Exception(ex);
        }

        return userLocationResponseMapper.userLocationListToUserLocationResponseList(historicalLocations);
    }
}