package com.example.ExpenseTracker.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ExpenseTracker.entities.Record;
import com.example.ExpenseTracker.repository.RecordRepository;

@Service
public class RecordService {
	
	@Autowired
	private RecordRepository recordRepository;
	
	public List<Record> getAllRecords() {
		
        return (List<Record>) recordRepository.findAll();
        
    }
	
	public Record createRecord(Record record) {
		
		record.setDate(new Date());
		
		return recordRepository.save(record);
		
	}
	
	public void deleteRecord(Integer id) {
		
		recordRepository.deleteById(id);
		
	}

}
