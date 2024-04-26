package com.example.productsdelivery.repo;

import com.example.productsdelivery.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepo extends JpaRepository<StockModel, Integer> {

}
