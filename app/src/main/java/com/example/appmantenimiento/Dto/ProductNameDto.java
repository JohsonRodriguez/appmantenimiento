package com.example.appmantenimiento.Dto;

public class ProductNameDto {
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductNameDto(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "ProductNameDto{" +
                "productName='" + productName + '\'' +
                '}';
    }
}
