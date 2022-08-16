package com.example.appmantenimiento.Dto;

import java.io.Serializable;

public class OuputDto implements Serializable {
    private float amount;
    private String productName;
    private String productBrand;
    private String users;
    private String employee;
    private String location;
    private String unit;

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

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public OuputDto() {
    }

    public OuputDto(float amount, String productName, String productBrand, String users, String employee, String location, String unit) {
        this.amount = amount;
        this.productName = productName;
        this.productBrand = productBrand;
        this.users = users;
        this.employee = employee;
        this.location = location;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "OuputDto{" +
                "amount=" + amount +
                ", productName='" + productName + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", users='" + users + '\'' +
                ", employee='" + employee + '\'' +
                ", location='" + location + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
