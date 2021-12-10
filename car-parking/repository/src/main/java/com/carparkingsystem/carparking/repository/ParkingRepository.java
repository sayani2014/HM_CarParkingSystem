package com.carparkingsystem.carparking.repository;

import com.carparkingsystem.carparking.model.Parking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingRepository extends MongoRepository<Parking, String> {
    Optional<Parking> findBySpacename(String spacename);
    void deleteBySpacename(String spacename);
}
