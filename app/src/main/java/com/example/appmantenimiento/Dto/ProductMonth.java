package com.example.appmantenimiento.Dto;

public class ProductMonth {
    private String name;
    private Double total;
    private String unit;

    public ProductMonth(String name, Double total, String unit) {
        this.name = name;
        this.total = total;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "ProductMonth{" +
                "name='" + name + '\'' +
                ", total=" + total +
                ", unit='" + unit + '\'' +
                '}';
    }
}
