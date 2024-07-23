package com.api.SchoolMongoDB.controller;

import com.api.SchoolMongoDB.dto.UserMongoDBDto;
import com.api.SchoolMongoDB.service.UserMongoDBService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserMongoDBControllerTest {

    @InjectMocks
    private UserMongoDBController userMongoDBController;

    @Mock
    private UserMongoDBService userMongoDBService;

    @Test
    public void findAllUsersShouldWork() {
        // Preparacion
        UserMongoDBDto user1 = new UserMongoDBDto();
        user1.setId("1");
        user1.setName("Luis");
        user1.setEmail("luis@example.com");

        UserMongoDBDto user2 = new UserMongoDBDto();
        user2.setId("2");
        user2.setName("Karin");
        user2.setEmail("karin@example.com");

        when(userMongoDBService.getAll()).thenReturn(Arrays.asList(user1, user2));

        // Ejecucion
        List<UserMongoDBDto> users = userMongoDBController.findAllUsers().getBody();

        // Validacion
        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("Luis", users.get(0).getName());
        assertEquals("Karin", users.get(1).getName());
    }

    @Test
    public void findByIdShouldWork() {
        //Preparacion
        UserMongoDBDto user = new UserMongoDBDto();
        user.setId("1");
        user.setName("Luis");
        user.setEmail("luis@example.com");

        when(userMongoDBService.getById("1")).thenReturn(user);

        // Ejecucion
        UserMongoDBDto foundUser = userMongoDBController.findById("1").getBody();

        // Validacion
        assertNotNull(foundUser);
        assertEquals("Luis", foundUser.getName());
        assertEquals("luis@example.com", foundUser.getEmail());
    }

    @Test
    public void createUserShouldWork() {
        // Preparacion
        UserMongoDBDto userDto = new UserMongoDBDto();
        userDto.setName("Luis");
        userDto.setEmail("luis@example.com");

        UserMongoDBDto savedUserDto = new UserMongoDBDto();
        savedUserDto.setId("1");
        savedUserDto.setName("Luis");
        savedUserDto.setEmail("luis@example.com");

        when(userMongoDBService.save(userDto)).thenReturn(savedUserDto);

        // Ejecucion
        UserMongoDBDto savedUser = userMongoDBController.saveUser(userDto).getBody();

        // Validacion
        assertNotNull(savedUser);
        assertEquals("1", savedUser.getId());
        assertEquals("Luis", savedUser.getName());
        assertEquals("luis@example.com", savedUser.getEmail());
    }

    @Test
    public void updateUserShouldWork() {
        // Preparacion
        String id = "1";
        UserMongoDBDto userDto = new UserMongoDBDto();
        userDto.setId(id);
        userDto.setName("Luis");
        userDto.setEmail("luis@example.com");

        UserMongoDBDto updatedUserDto = new UserMongoDBDto();
        updatedUserDto.setId(id);
        updatedUserDto.setName("Luis Updated");
        updatedUserDto.setEmail("luisupdated@example.com");

        when(userMongoDBService.update(userDto, id)).thenReturn(updatedUserDto);

        // Ejecucion
        UserMongoDBDto responseUser = userMongoDBController.updateUser(userDto, id).getBody();

        // Validacion
        assertNotNull(responseUser);
        assertEquals(updatedUserDto.getId(), responseUser.getId());
        assertEquals(updatedUserDto.getName(), responseUser.getName());
        assertEquals(updatedUserDto.getEmail(), responseUser.getEmail());
    }

    @Test
    public void deleteUserShouldWork() {
        // Preparacion
        String id = "1";

        // Ejecucion
        userMongoDBController.delete(id);

        // Validacion
        verify(userMongoDBService, times(1)).delete(id);
    }

}