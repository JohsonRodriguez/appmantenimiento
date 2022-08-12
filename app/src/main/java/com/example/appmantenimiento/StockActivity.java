package com.example.appmantenimiento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.example.appmantenimiento.Entity.Stock;
import com.example.appmantenimiento.adapter.StockAdapter;
import com.example.appmantenimiento.api.ApiClient;
import com.example.appmantenimiento.Entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StockActivity extends AppCompatActivity {

    ImageButton btnMenu;
    RecyclerView recyclerView;
    StockAdapter stockAdapter;
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
        btnMenu.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
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
                    Log.e("ok", stockRespons.toString());
                    stockAdapter.setData(stockRespons);
                    recyclerView.setAdapter(stockAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Stock>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());
            }
        });
    }
}