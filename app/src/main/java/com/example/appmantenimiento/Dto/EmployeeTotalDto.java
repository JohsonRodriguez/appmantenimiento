package com.example.appmantenimiento.Dto;

import java.io.Serializable;

public class EmployeeTotalDto implements Serializable {
    private Long id;
    private String name;
    private String lastname;
    private Double total;

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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public EmployeeTotalDto(Long id, String name, String lastname, Double total) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.total = total;
    }

    @Override
    public String toString() {
        return "EmployeeTotalDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", total=" + total +
                '}';
    }
}
