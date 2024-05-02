package com.example.productsdelivery.repo;

import com.example.productsdelivery.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<RoleModel, Integer>  {
}
