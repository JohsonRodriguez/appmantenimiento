package com.example.appmantenimiento.Dto;

public class ProductBrandDto {
    private String productBrand;

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public ProductBrandDto(String productBrand) {
        this.productBrand = productBrand;
    }

    @Override
    public String toString() {
        return "ProductBrandDto{" +
                "productBrand='" + productBrand + '\'' +
                '}';
    }
}
