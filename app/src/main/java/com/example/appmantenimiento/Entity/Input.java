package com.example.appmantenimiento.Entity;

import java.io.Serializable;

public class Input implements Serializable {

    private Long amount;
    private Long product;
    private Long users;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
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

    @Override
    public String toString() {
        return "Input{" +
                "amount=" + amount +
                ", product=" + product +
                ", users=" + users +
                '}';
    }
}
