package com.example.appmantenimiento.Entity;

import java.io.Serializable;

public class Stock implements Serializable {
    private Long id;
    private String product;
    private String brand;
    private float stock;
    private String unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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

    public Stock(Long id, String product, String brand, float stock, String unit) {
        this.id = id;
        this.product = product;
        this.brand = brand;
        this.stock = stock;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", brand='" + brand + '\'' +
                ", stock=" + stock +
                ", unit='" + unit + '\'' +
                '}';
    }
}
