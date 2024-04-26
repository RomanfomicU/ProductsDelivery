package com.example.productsdelivery.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "stocks")
public class StockModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String status;
    private Timestamp start_time;
    private Timestamp end_time;

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStartTime(Timestamp time) {
        this.start_time = time;
    }
    public Timestamp getStartTime() {
        return this.start_time;
    }
    public void setEndTime(Timestamp time) {
        this.end_time = time;
    }
    public Timestamp getEndTime() {
        return this.end_time;
    }
}
