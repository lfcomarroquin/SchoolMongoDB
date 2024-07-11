package com.api.SchoolMongoDB.controller;

import com.api.SchoolMongoDB.dto.UserMongoDBDto;
import com.api.SchoolMongoDB.service.UserMongoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UsersMongoDB")
public class UserMongoDBController {
    @Autowired
    private UserMongoDBService userMongoService;

    @GetMapping("/")
    public ResponseEntity<List<UserMongoDBDto>> findAllUsers(){
        List<UserMongoDBDto> listUsers = this.userMongoService.getAll();
        return ResponseEntity.ok(listUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserMongoDBDto> findById(@PathVariable("id") String id){
        UserMongoDBDto user = this.userMongoService.getById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/")
    public ResponseEntity<UserMongoDBDto> saveUser(@RequestBody UserMongoDBDto userDto){
        UserMongoDBDto savedUser = this.userMongoService.save(userDto);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserMongoDBDto> updateUser(@RequestBody UserMongoDBDto userDto, @PathVariable("id") String id){
        UserMongoDBDto updatedUser = this.userMongoService.update(userDto, id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        this.userMongoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}