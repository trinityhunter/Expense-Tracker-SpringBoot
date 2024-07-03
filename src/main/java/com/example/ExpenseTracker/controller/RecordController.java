package com.example.ExpenseTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ExpenseTracker.entities.Record;
import com.example.ExpenseTracker.services.RecordService;

@RestController
@CrossOrigin
@RequestMapping("/track")
public class RecordController {
	
	@Autowired
	private RecordService recordService;
	
	@GetMapping("/")
	public ResponseEntity<List<Record>> getAllRecords(){
		
		List<Record> questions = recordService.getAllRecords();
		
        return ResponseEntity.ok(questions);
        
	}
	
	@PostMapping("/")
	public ResponseEntity<Record> createRecord(@RequestBody Record record){
		
		Record savedrecord = recordService.createRecord(record);
		
		return ResponseEntity.ok(savedrecord);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRecord(@PathVariable Integer id) {
		
		recordService.deleteRecord(id);
        
        return ResponseEntity.noContent().build();
        
    }

}
