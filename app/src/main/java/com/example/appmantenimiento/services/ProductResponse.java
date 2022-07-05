package com.example.appmantenimiento.services;

import java.io.Serializable;

public class ProductResponse implements Serializable {
    private Long id;
    private  String name ;
    private String brand;
    private float stock;
    private String unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    @Override
    public String toString() {
        return "RegisterResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", stock='" + stock + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
