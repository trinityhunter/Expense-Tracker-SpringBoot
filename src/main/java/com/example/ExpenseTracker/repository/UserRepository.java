package com.example.ExpenseTracker.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.ExpenseTracker.entities.User;

public interface UserRepository extends CrudRepository<User, String> {
	Optional<User> findByEmail(String email);
}
