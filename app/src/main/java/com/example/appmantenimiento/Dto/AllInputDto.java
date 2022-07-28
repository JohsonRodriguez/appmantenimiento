package com.example.appmantenimiento.Dto;

import com.example.appmantenimiento.Entity.Product;

import java.io.Serializable;

public class AllInputDto implements Serializable {
    private Double amount;
    private String productName;
    private String brandName;
    private String userName;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    public AllInputDto(Double amount, String productName, String brandName, String userName) {
        this.amount = amount;
        this.productName = productName;
        this.brandName = brandName;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "AllInputDto{" +
                "amount=" + amount +
                ", productName='" + productName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
