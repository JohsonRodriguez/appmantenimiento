package com.example.appmantenimiento.Dto;

public class ProductDto {
    private String productName;
    private String brandName;
    private String unit;

    public ProductDto() {

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ProductDto(String productName, String brandName, String unit) {
        this.productName = productName;
        this.brandName = brandName;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "productName='" + productName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
