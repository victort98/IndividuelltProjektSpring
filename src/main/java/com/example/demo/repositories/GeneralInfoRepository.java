package com.example.demo.repositories;

import com.example.demo.entities.NameAndUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneralInfoRepository extends MongoRepository<NameAndUrl, String> {
    Optional<NameAndUrl> findByName(String name);
}
