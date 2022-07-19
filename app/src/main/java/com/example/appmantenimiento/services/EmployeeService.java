package com.example.appmantenimiento.services;

import com.example.appmantenimiento.Entity.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeService {
    @GET("employee/all")
    Call<List<Employee>> getEmployees();
}
