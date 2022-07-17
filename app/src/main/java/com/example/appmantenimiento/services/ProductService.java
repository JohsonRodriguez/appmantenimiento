package com.example.appmantenimiento.services;

import com.example.appmantenimiento.Entity.Input;
import com.example.appmantenimiento.Entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductService {
    @GET("product/all")
    Call<List<Product>> getProducts();

    @GET("product/findProduct/{name}")
    Call<Product> getOneProduct(@Path("name") String name);

    @POST("input/add")
    Call<Input> createInput(@Body Input input);



}

