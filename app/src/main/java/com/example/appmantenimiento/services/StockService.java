package com.example.appmantenimiento.services;

import android.content.Context;

import com.example.appmantenimiento.Entity.Input;
import com.example.appmantenimiento.Entity.Product;
import com.example.appmantenimiento.Entity.Stock;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface StockService {
    @GET("stock/all")
    Call<List<Stock>> getStocks();

}

