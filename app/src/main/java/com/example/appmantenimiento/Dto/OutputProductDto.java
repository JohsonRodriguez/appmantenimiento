package com.example.appmantenimiento.Dto;

public class OutputProductDto {

    private String product;
    private String date;

    public OutputProductDto() {

    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public OutputProductDto(String product, String date) {
        this.product = product;
        this.date = date;
    }

    @Override
    public String toString() {
        return "OutputProductDto{" +
                "product='" + product + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
