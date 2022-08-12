package com.example.appmantenimiento.Dto;

import android.content.ServiceConnection;

import java.io.Serializable;

public class AllOutputs implements Serializable {
    private String productName;
    private String locationName;
    private String userName;
    private String employeeName;
    private String employeeLastname;
    private Float amount;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeLastname() {
        return employeeLastname;
    }

    public void setEmployeeLastname(String employeeLastname) {
        this.employeeLastname = employeeLastname;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public AllOutputs(String productName, String locationName, String userName, String employeeName, String employeeLastname, Float amount) {
        this.productName = productName;
        this.locationName = locationName;
        this.userName = userName;
        this.employeeName = employeeName;
        this.employeeLastname = employeeLastname;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AllOutputs{" +
                "productName='" + productName + '\'' +
                ", locationName='" + locationName + '\'' +
                ", userName='" + userName + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", employeeLastname='" + employeeLastname + '\'' +
                ", amount=" + amount +
                '}';
    }
}
