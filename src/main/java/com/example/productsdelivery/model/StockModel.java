package com.example.productsdelivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.sql.Timestamp;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "stocks")
public class StockModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    private String status;

    @Column(name = "address")
    private String address;

    @Column(name = "open_time")
    private LocalTime open_time;

    @Column(name = "close_time")
    private LocalTime close_time;

}
