package com.example.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entity.Stock;
import com.example.springboot.repository.StockRepository;


@Service
public class StockService {
	 
	
    @Autowired
    private StockRepository repo;

    public Stock addStock(Stock s) {
		return repo.save(s);
	}
	public List<Stock> getAllStock(){
		return repo.findAll();
	}
	public Stock getStockById(int id) {
		if(repo.findById(id).isPresent()) {
			return repo.findById(id).get();
		}
		else {
			return null;
		}
		
	}
	 
    
	public Stock updateStock(int id, Stock newStock) {
		if(repo.findById(id).isPresent()) {
			Stock oldStock= repo.findById(id).get();
			oldStock.setName(newStock.getName());
			oldStock.setPrice(newStock.getPrice());
			return repo.save(oldStock);
		}
		else {
			return null;
		}
	}
	
	
	public boolean deleteStock(int id) {
		if(repo.findById(id).isPresent()) {
			repo.deleteById(id);
			return true;
		}
		
		else {
			return false;
		}
	}
	
}
