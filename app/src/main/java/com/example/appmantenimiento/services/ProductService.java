package com.example.appmantenimiento.services;

import android.content.Context;

import com.example.appmantenimiento.Dto.ProductBrandDto;
import com.example.appmantenimiento.Dto.ProductDto;
import com.example.appmantenimiento.Dto.ProductNameDto;
import com.example.appmantenimiento.Entity.Input;
import com.example.appmantenimiento.Entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductService {
    @GET("product/allname")
    Call<List<ProductNameDto>>getProducts();

    @GET("product/all")
    Call<List<Product>>getProduct();

    @POST("product/add")
    Call<Void> createProduct(@Body ProductDto productDto);

    @GET("product/findProduct/{name}")
   /* Call<List<ProductBrandDto>>getBrands(@Path("name") String name);*/
    Call<Product>getBrands(@Path("name") String name);


}
