package com.example.ExpenseTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ExpenseTracker.payload.LoginRequest;
import com.example.ExpenseTracker.payload.LoginResponse;
import com.example.ExpenseTracker.payload.SignupRequest;
import com.example.ExpenseTracker.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class UserController {
	
	 @Autowired
	    private UserService authService;

	    @PostMapping("/signup")
	    public ResponseEntity<?> registerUser(@jakarta.validation.Valid @RequestBody SignupRequest signUpRequest) {
	        try {
	            LoginResponse response = authService.signup(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getPassword());
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            return ResponseEntity.status(400).body(e.getMessage());
	        }
	    }

	    @PostMapping("/login")
	    public ResponseEntity<?> authenticateUser(@jakarta.validation.Valid @RequestBody LoginRequest loginRequest) {
	        try {
	            LoginResponse response = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            return ResponseEntity.status(400).body(e.getMessage());
	        }
	    }

}
