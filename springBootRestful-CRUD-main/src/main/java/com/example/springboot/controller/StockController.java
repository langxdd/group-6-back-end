package com.example.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entity.Stock;
import com.example.springboot.service.StockService;

@RestController
@RequestMapping("/api/stock")
public class StockController {
	
	@Autowired
	private StockService service;
	
	@PostMapping("/")
	public ResponseEntity<Stock> addStock(@RequestBody Stock s){
		
		Stock stock= service.addStock(s);
		
		if(stock!=null)
			return new ResponseEntity<Stock>(stock,HttpStatus.CREATED);
		else
			return new ResponseEntity<Stock>(stock,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@GetMapping("/")
	public List<Stock> getAllStock(){
		return service.getAllStock();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Stock>getStockById (@PathVariable int id){
		Stock stock= service.getStockById(id);
		
		if(stock!=null)
			return new ResponseEntity<Stock>(stock, HttpStatus.OK);
		else
			return new ResponseEntity<Stock>(stock, HttpStatus.NOT_FOUND);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateStock(@PathVariable int id,@RequestBody Stock newStock){
		Stock stock= service.updateStock(id, newStock);
		
		if (stock!=null)
			return new ResponseEntity<Object>(stock,HttpStatus.OK);
		else
			return new ResponseEntity<Object>("Stockser Available to Update",HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deleteStock(@PathVariable int id){
		boolean result = service.deleteStock(id);
		if(result) 
			return new ResponseEntity<String>("Object Deleted",HttpStatus.OK);
		else
			return new ResponseEntity<String>("NO user Found", HttpStatus.NOT_FOUND);
		
	}

}
