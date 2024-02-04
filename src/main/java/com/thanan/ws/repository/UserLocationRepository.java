package com.thanan.ws.repository;

import com.thanan.ws.entity.UserLocation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLocationRepository extends JpaRepository<UserLocation, Integer> {
    UserLocation findTopByUserIdOrderByTimeStampDesc(int user_id);
    List<UserLocation> findByUserIdOrderByTimeStampDesc(int user_id, PageRequest pageRequest);
}
