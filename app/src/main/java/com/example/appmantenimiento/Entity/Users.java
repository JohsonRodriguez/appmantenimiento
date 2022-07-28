package com.example.appmantenimiento.Entity;

import java.io.Serializable;

public class Users implements Serializable {
    private Float amount;
    private String productName;
    private String brandName;
    private String userName;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Users(Float amount, String productName, String brandName, String userName) {
        this.amount = amount;
        this.productName = productName;
        this.brandName = brandName;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Users{" +
                "amount=" + amount +
                ", productName='" + productName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
