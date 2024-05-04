package com.example.productsdelivery.repo;

import com.example.productsdelivery.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo  extends JpaRepository<OrderModel, Integer> {
    List<OrderModel> findByIdUser(Integer userId);
}
