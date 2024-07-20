package com.api.SchoolMongoDB.service;

import com.api.SchoolMongoDB.config.JwtService;
import com.api.SchoolMongoDB.dto.auth.AuthDto;
import com.api.SchoolMongoDB.dto.auth.LoginDto;
import com.api.SchoolMongoDB.dto.auth.RegisterDto;
import com.api.SchoolMongoDB.entity.UserMongoDBEntity;
import com.api.SchoolMongoDB.repository.UserMongoDBRepository;
import com.api.SchoolMongoDB.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserMongoDBRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthDto login (final LoginDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByEmail(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return new AuthDto(token);
    }

    public AuthDto register (final RegisterDto request) {
        UserMongoDBEntity user = new UserMongoDBEntity();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        return new AuthDto(this.jwtService.getToken(user));
    }

}