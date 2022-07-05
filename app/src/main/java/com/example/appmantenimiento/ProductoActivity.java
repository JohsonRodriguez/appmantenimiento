package com.example.appmantenimiento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.appmantenimiento.adapter.ProductAdapter;
import com.example.appmantenimiento.api.ApiClient;
import com.example.appmantenimiento.services.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoActivity extends AppCompatActivity {
     ImageButton btnMenu;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        btnMenu=findViewById(R.id.btn_atras);


        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        productAdapter = new ProductAdapter(this::ClickedProduct);
        getAllProducts();

        btnMenu.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        });

    }
    public void getAllProducts(){
        Call<List<ProductResponse>> productlist = ApiClient.getProducts().getProducts();
        productlist.enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                if(response.isSuccessful()){
                    List<ProductResponse> productResponses = response.body();
                    productAdapter.setData(productResponses);
                    recyclerView.setAdapter(productAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());
            }
        });
    }
    public void ClickedProduct(ProductResponse productResponse) {

    }
}