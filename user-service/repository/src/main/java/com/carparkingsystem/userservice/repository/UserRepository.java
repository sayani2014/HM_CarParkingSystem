package com.carparkingsystem.userservice.repository;

import com.carparkingsystem.userservice.model.ParkingDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<ParkingDetails, Long> {
    @Query("{entrydatetime : {$lt: ?0}}")
    List<ParkingDetails> findByEntrydatetime(LocalDateTime entrydatetime);
}
