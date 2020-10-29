package com.example.demo.repositories;

import com.example.demo.entities.GeneralInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneralInfoRepository extends MongoRepository<GeneralInfo, String> {
    Optional<GeneralInfo> findByName(String name);
}
