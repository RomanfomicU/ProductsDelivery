package com.example.productsdelivery.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    public List<Map<String, String>> stocks = new ArrayList<Map<String, String>>() {{
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

    @GetMapping
    public List<Map<String, String>> list() {
        return stocks;
    }

    @GetMapping("{id}")
    public Map<String, String> getStock(@PathVariable String id) {
        return stocks.stream()
                .filter(stocks ->stocks.get("id").equals(id))
                .findFirst()
                .orElseThrow();
    }
}
