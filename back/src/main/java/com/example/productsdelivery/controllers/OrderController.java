package com.example.productsdelivery.controllers;

import com.example.productsdelivery.model.OrderModel;
import com.example.productsdelivery.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderRepo orderRepo;

    @GetMapping
    @PreAuthorize("hasAuthority('3') || hasAuthority('2')")
    public List<OrderModel> getOrderList() {
        return orderRepo.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('3') || hasAuthority('2') || hasAuthority('1')")
    public Optional<OrderModel> getOrder(@PathVariable String id) {
        return orderRepo.findById(Integer.valueOf(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('3') || hasAuthority('1')")
    public OrderModel createOrder(@RequestBody OrderModel order) {
        OrderModel newOrder = orderRepo.save(order);
        return newOrder;
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('3')")
    public OrderModel updateOrder(@PathVariable String id, @RequestBody OrderModel order) {
        OrderModel newOrder = orderRepo.findById(Integer.valueOf(id)).orElse(null);
        if(newOrder == null) return null;
        if (order.getStatus() != null) {
            newOrder.setStatus(order.getStatus());
        }
        if (order.getEndPoint() != null) {
            newOrder.setEndPoint(order.getEndPoint());
        }
        if (order.getIdUser() != null) {
            newOrder.setIdUser(order.getIdUser());
        }
        if (order.getIdCourier() != null) {
            newOrder.setIdCourier(order.getIdCourier());
        }
        if (order.getIdStock() != null) {
            newOrder.setIdStock(order.getIdStock());
        }
        newOrder.setId(Integer.valueOf(id));
        orderRepo.save(newOrder);
        return newOrder;
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('3') || hasAuthority('2') || hasAuthority('1')")
    public void deleteOrder(@PathVariable String id) {
        orderRepo.deleteById(Integer.valueOf(id));
    }
}
