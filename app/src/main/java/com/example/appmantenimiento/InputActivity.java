package com.example.appmantenimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmantenimiento.Dto.InputDto;
import com.example.appmantenimiento.Dto.ProductBrandDto;
import com.example.appmantenimiento.Dto.ProductNameDto;
import com.example.appmantenimiento.Entity.Brand;
import com.example.appmantenimiento.Entity.Input;
import com.example.appmantenimiento.Entity.Product;
import com.example.appmantenimiento.Entity.Stock;
import com.example.appmantenimiento.api.ApiClient;

public class InputActivity extends AppCompatActivity {
    ArrayList<String> productsName,productsBrand;

    Spinner spinner_Product,spinner_Brand;
    TextView unit;
    ImageButton btnMenu;
    Button btnAdd;
    EditText _edtAmount;
    String productName, productBrand,_users,_unit;
    Long _amount;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        btnMenu=findViewById(R.id.btn_atras);
        btnAdd=findViewById(R.id.btn_Add);
        _edtAmount=findViewById(R.id.edtAmount);
        unit=findViewById(R.id._tvUnidad);
        productsName=new ArrayList<>();
        spinner_Product=findViewById(R.id.spinerProducts);
        spinner_Brand=findViewById(R.id.spinerBrands);

        getAllProducts();

        //Button Back
        btnMenu.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        });
       //Select Spinner Product
        spinner_Product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               productName=(String) spinner_Product.getSelectedItem();
                searchBrand(productName);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Select Brand Spinner
        spinner_Brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                productBrand=(String) spinner_Brand.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Button Agregar
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _amount= Long.valueOf(_edtAmount.getText().toString());
                _users= "LEONARDO GUZMAN";
                sendAddProduct(_amount,productName,productBrand,_users,_unit);
            }

            private void sendAddProduct(Long amount, String productName, String productBrand, String users, String _unit) {
                InputDto iproduct= new InputDto();
                iproduct.setProduct(productName);
                iproduct.setBrand(productBrand);
                iproduct.setAmount(amount);
                iproduct.setUsers(users);
                iproduct.setUnit(_unit);
                Call<Void> inputProduct= ApiClient.getInputService().createInput(iproduct);
                inputProduct.enqueue(new Callback<Void>(){
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getApplicationContext(), "Producto guardado", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(getApplicationContext(), InputActivity.class));
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
            });
        }
    });}



    private void searchBrand(String productName) {
        final Context context = this;
        productsBrand=new ArrayList<>();
        Call<Product> productObt = ApiClient.getProductService().getBrands(productName);
       productObt.enqueue(new Callback<Product>() {
           @Override
           public void onResponse(Call<Product> call, Response<Product> response) {
               for (Brand pb : response.body().getBrands()) {
                   productsBrand.add(pb.getName());
               }
                _unit=response.body().getUnit();
               unit.setText(_unit);
               ArrayAdapter<String> adapter = new ArrayAdapter<>( context,android.R.layout.simple_spinner_item,productsBrand);
               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               spinner_Brand.setAdapter(adapter);
           }

           @Override
           public void onFailure(Call<Product> call, Throwable t) {

           }
       });
    }


    public void getAllProducts() {
        productsName = new ArrayList<>();

        Call<List<Product>> productlist = ApiClient.getProductService().getProduct();
        final Context context = this;
       productlist.enqueue(new Callback<List<Product>>() {
           @Override
           public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

               for ( Product p : response.body()) {
                   productsName.add(p.getName());
               }

               ArrayAdapter<String> adapter = new ArrayAdapter<>( context,android.R.layout.simple_spinner_item,productsName);
               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               spinner_Product.setAdapter(adapter);
           }

           @Override
           public void onFailure(Call<List<Product>> call, Throwable t) {

           }
       });

    }

}
