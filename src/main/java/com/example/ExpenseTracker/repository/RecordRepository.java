package com.example.ExpenseTracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.ExpenseTracker.entities.Record;

public interface RecordRepository extends CrudRepository<Record, Integer> {

}
