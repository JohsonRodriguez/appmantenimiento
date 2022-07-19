package com.example.appmantenimiento.services;


import com.example.appmantenimiento.Entity.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocationService {
    @GET("location/all")
    Call<List<Location>> getLocations();
}
