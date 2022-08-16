package com.example.appmantenimiento.services;

import com.example.appmantenimiento.Dto.Login;
import com.example.appmantenimiento.Dto.ProductDto;
import com.example.appmantenimiento.Dto.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("users/login")
    Call<UserDto> login(@Body Login login);
}
