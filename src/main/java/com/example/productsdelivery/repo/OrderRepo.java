package com.example.productsdelivery.repo;

import com.example.productsdelivery.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo  extends JpaRepository<OrderModel, Integer> {
}
