package com.api.SchoolMongoDB.service;

import com.api.SchoolMongoDB.dto.UserMongoDBDto;
import com.api.SchoolMongoDB.entity.UserMongoDBEntity;
import com.api.SchoolMongoDB.repository.UserMongoDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMongoDBService {
    @Autowired
    private UserMongoDBRepository userMongoDBRepository;

    public List<UserMongoDBDto> getAll() {
        return this.userMongoDBRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public UserMongoDBDto getById(String id) {
        return this.userMongoDBRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public UserMongoDBDto save(UserMongoDBDto user) {
        UserMongoDBEntity entity = new UserMongoDBEntity();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        UserMongoDBEntity entitySaved = this.userMongoDBRepository.save(entity);
        UserMongoDBDto saved = this.toDto(entitySaved);
        return saved;
    }

    public UserMongoDBDto update(UserMongoDBDto user, String id) {
        UserMongoDBEntity entity = this.userMongoDBRepository.findById(id)
                .orElse(null);
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        UserMongoDBEntity entitySaved = this.userMongoDBRepository.save(entity);
        UserMongoDBDto saved = this.toDto(entitySaved);
        return saved;
    }

    public void delete(String id) {
        UserMongoDBEntity entity = this.userMongoDBRepository.findById(id)
                .orElse(null);
        this.userMongoDBRepository.delete(entity);
    }

    private UserMongoDBDto toDto(UserMongoDBEntity entity) {
        return new UserMongoDBDto(entity.getId(), entity.getName(), entity.getEmail());
    }
}