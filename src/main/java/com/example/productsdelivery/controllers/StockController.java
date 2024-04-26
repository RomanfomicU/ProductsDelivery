package com.example.productsdelivery.controllers;

import com.example.productsdelivery.exceptions.notFoundException;
import com.example.productsdelivery.model.StockModel;
import com.example.productsdelivery.repo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private int counter = 4;
    @Autowired
    private StockRepo stockRepo;

    @GetMapping
    public List<StockModel> list() {
        return stockRepo.findAll();
    }
    @GetMapping("{id}")
    public Optional<StockModel> getStock(@PathVariable String id) {
        return stockRepo.findById(Integer.valueOf(id));
    }
    /*@PostMapping
    public Map<String, String> createStock(@RequestBody Map<String, String> stock) {
        stock.put("id", String.valueOf(counter++));
        stocks.add(stock);
        return stock;
    }
    @PutMapping("{id}")
    public Map<String, String> updateStock(@PathVariable String id, @RequestBody Map<String, String> newStock) {
        Map<String, String> stock = getStockById(id);
        stock.putAll(newStock);
        stock.put("id", id);
        return stock;
    }
    @DeleteMapping("{id}")
    public void deleteStock(@PathVariable String id) {
        Map<String, String> stock = getStockById(id);
        stocks.remove(stock);
    }*/
}
