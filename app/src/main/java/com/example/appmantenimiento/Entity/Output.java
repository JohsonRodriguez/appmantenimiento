package com.example.appmantenimiento.Entity;

import java.io.Serializable;

public class Output implements Serializable {
    private float amount;
    private String productName;
    private String productBrand;
    private Long users;
    private Long employee;
    private Long location;

    public Output() {

    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public Long getUsers() {
        return users;
    }

    public void setUsers(Long users) {
        this.users = users;
    }

    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(Long employee) {
        this.employee = employee;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    public Output(float amount, String productName, String productBrand, Long users, Long employee, Long location) {
        this.amount = amount;
        this.productName = productName;
        this.productBrand = productBrand;
        this.users = users;
        this.employee = employee;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Output{" +
                "amount=" + amount +
                ", productName='" + productName + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", users=" + users +
                ", employee=" + employee +
                ", location=" + location +
                '}';
    }
}
