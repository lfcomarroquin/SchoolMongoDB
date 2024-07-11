package com.api.SchoolMongoDB.repository;

import com.api.SchoolMongoDB.entity.UserMongoDBEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoDBRepository extends MongoRepository<UserMongoDBEntity, String> {
}