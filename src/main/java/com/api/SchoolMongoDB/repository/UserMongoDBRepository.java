package com.api.SchoolMongoDB.repository;

import com.api.SchoolMongoDB.entity.UserMongoDBEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Este repositorio hereda los metodos desde MongoRepository
@Repository
public interface UserMongoDBRepository extends MongoRepository<UserMongoDBEntity, String> {

    Optional<UserMongoDBEntity> findByEmail(String email);

}