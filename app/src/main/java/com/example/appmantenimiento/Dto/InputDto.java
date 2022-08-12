package com.example.appmantenimiento.Dto;

import java.io.Serializable;

public class InputDto implements Serializable {
    private String day;
    private float amount;
    private String product;
    private String brand;
    private String users;
    private String unit;

    public InputDto() {

    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
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

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public InputDto(String day, float amount, String product, String brand, String users, String unit) {
        this.day = day;
        this.amount = amount;
        this.product = product;
        this.brand = brand;
        this.users = users;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "InputDto{" +
                "day='" + day + '\'' +
                ", amount=" + amount +
                ", product='" + product + '\'' +
                ", brand='" + brand + '\'' +
                ", users='" + users + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
