package com.example.productsdelivery.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    private String status;

    @Column(name = "end_point")
    private String endPoint;

    @JoinColumn(name = "id_user")
    private Integer idUser;

    @JoinColumn(name = "id_courier")
    private Integer idCourier;

    @JoinColumn(name = "id_stock")
    private Integer idStock;
}
