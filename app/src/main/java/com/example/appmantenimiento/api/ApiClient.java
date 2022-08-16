package com.example.appmantenimiento.api;

import com.example.appmantenimiento.services.EmployeeService;
import com.example.appmantenimiento.services.InputService;
import com.example.appmantenimiento.services.LocationService;
import com.example.appmantenimiento.services.OutputService;
import com.example.appmantenimiento.services.ProductService;
import com.example.appmantenimiento.services.StockService;
import com.example.appmantenimiento.services.UserService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://appmantenimiento.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    public static StockService getStocksService(){
        StockService stockService = getRetrofit().create(StockService.class);
        return stockService;
    }

    public static EmployeeService getEmployeeService(){
        EmployeeService employeeService=getRetrofit().create(EmployeeService.class);
        return employeeService;
    }

    public static LocationService getLocationService(){
        LocationService locationService=getRetrofit().create(LocationService.class);
        return locationService;
    }

    public static OutputService getOutputService(){
        OutputService outputService=getRetrofit().create(OutputService.class);
        return outputService;
    }

    public static InputService getInputService(){
        InputService inputService=getRetrofit().create(InputService.class);
        return inputService;
    }
    public static ProductService getProductService(){
        ProductService productService=getRetrofit().create(ProductService.class);
        return productService;
    }

    public static UserService getUserService(){
        UserService userService=getRetrofit().create(UserService.class);
        return userService;
    }

}
