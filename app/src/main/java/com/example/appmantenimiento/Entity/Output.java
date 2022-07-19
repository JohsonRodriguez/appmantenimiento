package com.example.appmantenimiento.Entity;

import java.io.Serializable;

public class Output implements Serializable {
    private float amount;
    private Long product;
    private Long users;
    private Long employee;
    private Long location;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public Long getUsers() {
        return users;
    }

    public void setUsers(Long users) {
        this.users = users;
    }

    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(Long employee) {
        this.employee = employee;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Output{" +
                "amount=" + amount +
                ", product=" + product +
                ", users=" + users +
                ", employee=" + employee +
                ", location=" + location +
                '}';
    }
}
