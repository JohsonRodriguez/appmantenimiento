package com.example.appmantenimiento.services;

import com.example.appmantenimiento.Dto.AllInputDto;
import com.example.appmantenimiento.Dto.InputDto;
import com.example.appmantenimiento.Entity.Input;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InputService {

    @POST("input/add")
    Call<Void> createInput(@Body InputDto inputDto);

    @POST("input/getall/{day}")
    Call<List<Input>> getAllInputs(@Path("day") String day);
}
