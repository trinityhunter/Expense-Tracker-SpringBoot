package com.example.ExpenseTracker.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ExpenseTracker.entities.User;
import com.example.ExpenseTracker.repository.UserRepository;
import com.example.ExpenseTracker.security.JwtTokenProvider;

@Service
public class UserService {

    @Autowired
    private UserRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public com.example.ExpenseTracker.payload.LoginResponse signup(String name, String email, String password) throws Exception {
        if (authRepository.findByEmail(email).isPresent()) {
            throw new Exception("Sorry, a user with this email already exists");
        }

        String hashedPassword = passwordEncoder.encode(password);

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);
        newUser.setJoinedOn(new Date());

        authRepository.save(newUser);

        String token = tokenProvider.generateToken(email, newUser.getEmail());

        return new com.example.ExpenseTracker.payload.LoginResponse(newUser, token);
    }

    public com.example.ExpenseTracker.payload.LoginResponse login(String email, String password) throws Exception {
    	User existingUser = authRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Please login with correct credentials"));

        if (!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new Exception("Please login with correct credentials");
        }

        String token = tokenProvider.generateToken(email, existingUser.getEmail());

        return new com.example.ExpenseTracker.payload.LoginResponse(existingUser, token);
    }
}