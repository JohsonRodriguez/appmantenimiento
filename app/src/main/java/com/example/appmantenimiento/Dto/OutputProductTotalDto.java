package com.example.appmantenimiento.Dto;

import java.io.Serializable;

public class OutputProductTotalDto implements Serializable {
    private String name;
    private Double total;
    private String unit;

    public OutputProductTotalDto(String name, Double total, String unit) {
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
        return "OutputProductTotalDto{" +
                "name='" + name + '\'' +
                ", total=" + total +
                ", unit='" + unit + '\'' +
                '}';
    }
}
