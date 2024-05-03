package com.example.productsdelivery.controllers;

import com.example.productsdelivery.model.StockModel;
import com.example.productsdelivery.repo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private StockRepo stockRepo;

    @GetMapping
    @PreAuthorize("hasAuthority('3') || hasAuthority('2')")
    public List<StockModel> getStockList() {
        return stockRepo.findAll();
    }
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('3') || hasAuthority('2')")
    public Optional<StockModel> getStock(@PathVariable String id) {
        return stockRepo.findById(Integer.valueOf(id));
    }
    @PostMapping
    @PreAuthorize("hasAuthority('3')")
    public StockModel createStock(@RequestBody StockModel stock) {
        StockModel newStock = stockRepo.save(stock);
        return newStock;
    }
    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('3')")
    public StockModel updateStock(@PathVariable String id, @RequestBody StockModel stock) {
        StockModel newStock = stockRepo.findById(Integer.valueOf(id)).orElse(null);
        if(newStock == null) return null;
        if (stock.getStatus() != null) {
            newStock.setStatus(stock.getStatus());
        }
        if (stock.getAddress() != null) {
            newStock.setAddress(stock.getAddress());
        }
        if (stock.getOpenTime() != null) {
            newStock.setOpenTime(stock.getOpenTime());
        }
        if (stock.getCloseTime() != null) {
            newStock.setCloseTime(stock.getCloseTime());
        }
        newStock.setId(Integer.valueOf(id));
        stockRepo.save(newStock);
        return newStock;
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('3')")
    public void deleteStock(@PathVariable String id) {
        stockRepo.deleteById(Integer.valueOf(id));
    }
}
