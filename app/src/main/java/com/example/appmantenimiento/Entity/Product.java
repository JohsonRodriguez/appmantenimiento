package com.example.appmantenimiento.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Product implements Serializable {
    private Long id;
    private  String name ;
    private String unit;
    private ArrayList<Brand> brands = new ArrayList<>();

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ArrayList<Brand> getBrands() {
        return brands;
    }

    public void setBrands(ArrayList<Brand> brands) {
        this.brands = brands;
    }

    public Product(Long id, String name, String unit, ArrayList<Brand> brands) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.brands = brands;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", brands=" + brands +
                '}';
    }
}
