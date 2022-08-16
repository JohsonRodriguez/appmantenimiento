package com.example.appmantenimiento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.example.appmantenimiento.Entity.Stock;
import com.example.appmantenimiento.adapter.StockAdapter;
import com.example.appmantenimiento.api.ApiClient;
import com.example.appmantenimiento.Entity.Product;
import com.lazyprogrammer.motiontoast.MotionStyle;
import com.lazyprogrammer.motiontoast.MotionToast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StockActivity extends AppCompatActivity {
    public static SharedPreferences preferences;
    ImageButton btnMenu;
    RecyclerView recyclerView;
    StockAdapter stockAdapter;
    String rol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        btnMenu=findViewById(R.id.btn_atras);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        stockAdapter = new StockAdapter(this::ClickedProduct);
        getAllStocks();
        preferences = getSharedPreferences(getPackageName()+ "_preferences", Context.MODE_PRIVATE);
        rol=preferences.getString("rol", "");

        btnMenu.setOnClickListener(v->{
            if(rol.equals("ADMIN")){
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }else{
                startActivity(new Intent(getApplicationContext(), MenuUserActivity.class));
            }
        });
    }

    private void ClickedProduct(Product product) {
    }

    public void getAllStocks(){
        Call<List<Stock>> stocklist = ApiClient.getStocksService().getStocks();
        stocklist.enqueue(new Callback<List<Stock>>() {
            @Override
            public void onResponse(Call<List<Stock>> call, Response<List<Stock>> response) {
                if(response.isSuccessful()){
                    List<Stock> stockRespons = response.body();
                    stockAdapter.setData(stockRespons);
                    recyclerView.setAdapter(stockAdapter);
                }else{
                    MotionToast motionToastR =  new MotionToast(StockActivity.this,0,
                            MotionStyle.LIGHT,
                            MotionStyle.ERROR,
                            MotionStyle.BOTTOM,
                            "ERROR",
                            "No se puedo cargar los productos",
                            MotionStyle.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Stock>> call, Throwable t) {
                MotionToast motionToastR =  new MotionToast(StockActivity.this,0,
                        MotionStyle.LIGHT,
                        MotionStyle.ERROR,
                        MotionStyle.BOTTOM,
                        "ERROR",
                        "Ocurrio un error inesperado",
                        MotionStyle.LENGTH_SHORT).show();
            }
        });
    }
}