package com.example.appmantenimiento.services;




import com.example.appmantenimiento.Dto.AllOutputs;
import com.example.appmantenimiento.Dto.EmployeeTotalDto;
import com.example.appmantenimiento.Dto.OuputDto;
import com.example.appmantenimiento.Dto.OutputProductDto;
import com.example.appmantenimiento.Dto.OutputProductTotalDto;
import com.example.appmantenimiento.Dto.ProductMonth;
import com.example.appmantenimiento.Entity.Output;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OutputService {
    @POST("output/add")
    Call<Void> createOutput(@Body OuputDto ouputDto);

    @POST("output/sum")
    Call<List<OutputProductTotalDto>> sumOutput(@Body OutputProductDto outputProductDto);

    @POST("output/sumemployee")
    Call<List<EmployeeTotalDto>> sumOutputEmployee(@Body OutputProductDto outputProductDto);

    @POST("output/allbyday/{day}")
    Call<List<Output>> getAllOutputsByDay(@Path("day") String day);

    @POST("output/allbymonth/{day}")
    Call<List<ProductMonth>> getAllProductsByMonth(@Path("day") String day);


}
