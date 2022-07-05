package com.example.appmantenimiento.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {
    @GET("product/all")
    Call<List<ProductResponse>> getProducts();
}
