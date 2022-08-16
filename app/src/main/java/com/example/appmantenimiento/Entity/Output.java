package com.example.appmantenimiento.Entity;

import java.io.Serializable;

public class Output implements Serializable {
    private Long id;
    private String day;
    private float amount;
    private String users;
    private String product;
    private String brand;
    private String location;
    private String employee;
    private String unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Output() {
    }

    public Output(Long id, String day, float amount, String users, String product, String brand, String location, String employee, String unit) {
        this.id = id;
        this.day = day;
        this.amount = amount;
        this.users = users;
        this.product = product;
        this.brand = brand;
        this.location = location;
        this.employee = employee;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Output{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", amount=" + amount +
                ", users='" + users + '\'' +
                ", product='" + product + '\'' +
                ", brand='" + brand + '\'' +
                ", location='" + location + '\'' +
                ", employee='" + employee + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
