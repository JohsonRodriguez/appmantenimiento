package com.example.appmantenimiento.services;




import com.example.appmantenimiento.Entity.Output;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OutputService {
    @POST("output/add")
    Call<Void> createOutput(@Body Output output);
}
