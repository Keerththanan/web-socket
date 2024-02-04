package com.thanan.ws.mapper.response;

import com.thanan.ws.entity.UserLocation;
import com.thanan.ws.model.response.UserLocationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserLocationResponseMapper {

    public abstract UserLocationResponse userLocationToUserLocationResponse(UserLocation userLocation);

    public abstract List<UserLocationResponse> userLocationListToUserLocationResponseList(List<UserLocation> userLocationList);
}
