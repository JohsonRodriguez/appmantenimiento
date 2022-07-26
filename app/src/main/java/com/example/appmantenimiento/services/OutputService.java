package com.example.appmantenimiento.services;




import com.example.appmantenimiento.Dto.OutputProductDto;
import com.example.appmantenimiento.Dto.OutputProductTotalDto;
import com.example.appmantenimiento.Entity.Output;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OutputService {
    @POST("output/add")
    Call<Void> createOutput(@Body Output output);

    @POST("output/sum")
    Call<List<OutputProductTotalDto>> sumOutput(@Body OutputProductDto outputProductDto);

}
