package com.thanan.ws.mapper.request;

import com.thanan.ws.entity.UserLocation;
import com.thanan.ws.model.request.UserLocationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserLocationRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timeStamp", expression = "java(java.time.LocalDateTime.now())")
    public abstract UserLocation userLocationRequestToUserLocation(UserLocationRequest userLocationRequest);
}
