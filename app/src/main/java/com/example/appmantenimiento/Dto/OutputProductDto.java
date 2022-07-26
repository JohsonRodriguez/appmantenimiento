package com.example.appmantenimiento.Dto;

public class OutputProductDto {

    private Long product;
    private String date;

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OutputProductDto{" +
                "product=" + product +
                ", date='" + date + '\'' +
                '}';
    }
}
