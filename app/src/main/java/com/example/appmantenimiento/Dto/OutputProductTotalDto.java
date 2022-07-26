package com.example.appmantenimiento.Dto;

import java.io.Serializable;

public class OutputProductTotalDto implements Serializable {
    private String name;
    private Double total;

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

    public OutputProductTotalDto(String name, Double total) {
        this.name = name;
        this.total = total;
    }

    @Override
    public String toString() {
        return "OutputProductTotalDto{" +
                "name='" + name + '\'' +
                ", total=" + total +
                '}';
    }


}
