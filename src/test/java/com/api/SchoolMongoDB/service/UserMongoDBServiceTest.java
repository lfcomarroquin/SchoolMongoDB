package com.api.SchoolMongoDB.service;

import com.api.SchoolMongoDB.dto.UserMongoDBDto;
import com.api.SchoolMongoDB.entity.UserMongoDBEntity;
import com.api.SchoolMongoDB.repository.UserMongoDBRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserMongoDBServiceTest {

    @InjectMocks
    private UserMongoDBService userMongoDBService;

    @Mock
    private UserMongoDBRepository userMongoDBRepository;

    @Test
    public void getAllShouldWork() {
        //Preparacion
        UserMongoDBEntity user1 = new UserMongoDBEntity();
        user1.setId("1");
        user1.setName("Luis");
        user1.setEmail("luis@example.com");

        UserMongoDBEntity user2 = new UserMongoDBEntity();
        user2.setId("2");
        user2.setName("Karin");
        user2.setEmail("karin@example.com");

        when(userMongoDBRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        //Ejecucion
        List<UserMongoDBDto> users = userMongoDBService.getAll();

        //Validacion
        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("Luis", users.get(0).getName());
        assertEquals("Karin", users.get(1).getName());
    }

    @Test
    public void findByIdShouldWork() {
        //Preparacion
        UserMongoDBEntity user = new UserMongoDBEntity();
        user.setId("1");
        user.setName("Luis");
        user.setEmail("luis@example.com");
        when(userMongoDBRepository.findById("1")).thenReturn(Optional.of(user));

        //Ejecucion
        UserMongoDBDto userDto = userMongoDBService.getById("1");

        //Validacion
        assert userDto.getId().equals("1");
        assert userDto.getName().equals("Luis");
        assert userDto.getEmail().equals("luis@example.com");
    }

    @Test
    public void saveUserShouldWork() {
        //Preparacion
        UserMongoDBDto userMongoDBDto = new UserMongoDBDto(null, "Luis", "luis@example.com");
        UserMongoDBEntity user = new UserMongoDBEntity();
        user.setName("Luis");
        user.setEmail("luis@example.com");

        UserMongoDBEntity savedUser = new UserMongoDBEntity();
        savedUser.setId("1");
        savedUser.setName("Luis");
        savedUser.setEmail("luis@example.com");

        when(userMongoDBRepository.save(any(UserMongoDBEntity.class))).thenReturn(savedUser);

        //Ejecucion
        UserMongoDBDto savedUserDto = userMongoDBService.save(userMongoDBDto);

        //Validacion
        assertNotNull(savedUserDto);
        assertEquals("1", savedUserDto.getId());
        assertEquals("Luis", savedUserDto.getName());
        assertEquals("luis@example.com", savedUserDto.getEmail());
    }

    @Test
    public void updateUserShouldWork() {
        //Preparacion
        UserMongoDBDto userMongoDBDto = new UserMongoDBDto(null, "Luis", "luis@example.com");

        UserMongoDBEntity user = new UserMongoDBEntity();
        user.setId("1");
        user.setName("Luis");
        user.setEmail("luis@example.com");

        when(userMongoDBRepository.findById("1")).thenReturn(Optional.of(user));
        when(userMongoDBRepository.save(any(UserMongoDBEntity.class))).thenReturn(user);

        //Ejecucion
        UserMongoDBDto updatedUser = userMongoDBService.update(userMongoDBDto, "1");

        //Validacion
        assertNotNull(updatedUser);
        assertEquals("1", updatedUser.getId());
        assertEquals("Luis", updatedUser.getName());
    }

    @Test
    public void deleteUserShouldWork() {
        //Preparacion
        UserMongoDBEntity user = new UserMongoDBEntity();
        user.setId("1");
        user.setName("Luis");
        user.setEmail("luis@example.com");
        when(userMongoDBRepository.findById("1")).thenReturn(Optional.of(user));

        //Ejecucion
        userMongoDBService.delete("1");

        //Validacion
        verify(userMongoDBRepository).delete(user);
    }

}