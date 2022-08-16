package com.example.appmantenimiento.Dto;

import java.io.Serializable;

public class EmployeeTotalDto implements Serializable {

    private String employee;
    private Double total;
    private String unit;

    public EmployeeTotalDto(String employee, Double total, String unit) {
        this.employee = employee;
        this.total = total;
        this.unit = unit;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
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
        return "EmployeeTotalDto{" +
                "employee='" + employee + '\'' +
                ", total=" + total +
                ", unit='" + unit + '\'' +
                '}';
    }
}
