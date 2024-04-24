package com.example.productsdelivery.controllers;

import com.example.productsdelivery.exceptions.notFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private int counter = 4;
    private List<Map<String, String>> stocks = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1"); put("status", "open"); put("open_time", "8:00"); put("close_time", "22:00");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2"); put("status", "close"); put("open_time", "8:00"); put("close_time", "22:00");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3"); put("status", "open"); put("open_time", "8:00"); put("close_time", "22:00");
        }});
    }};


    private Map<String, String> getStockById(@PathVariable String id) {
        return stocks.stream()
                .filter(stocks ->stocks.get("id").equals(id))
                .findFirst()
                .orElseThrow(notFoundException::new);
    }
    @GetMapping
    public List<Map<String, String>> list() {
        return stocks;
    }
    @GetMapping("{id}")
    public Map<String, String> getStock(@PathVariable String id) {
        return getStockById(id);
    }
    @PostMapping
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
    }
}
