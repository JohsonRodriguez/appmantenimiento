package com.example.appmantenimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appmantenimiento.Dto.ProductDto;
import com.example.appmantenimiento.api.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewProductActivity extends AppCompatActivity {
    //Initialize spinner
    EditText et_product,et_brand;
    Spinner spinner_unit;
    ArrayList inputName;
    Button btnadd;
    ImageButton back;
    String product, brand, unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        spinner_unit=findViewById(R.id.spinerUnit);
        et_product=findViewById(R.id.txtProduct);
        et_brand=findViewById(R.id.txtBrand);
        btnadd=findViewById(R.id.btn_AddProduct);
        back=findViewById(R.id.btn_back);
        getAllInputs();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });
        spinner_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                unit=(String) spinner_unit.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product=et_product.getText().toString().toUpperCase();
                brand=et_brand.getText().toString().toUpperCase();
                addProduct(product,brand,unit);
            }
        });

    }

    private void addProduct(String product, String brand, String unit) {
        ProductDto newproduct=new ProductDto();
        newproduct.setProductName(product);
        newproduct.setBrandName(brand);
        newproduct.setUnit(unit);
        Call<Void> inputProduct= ApiClient.getProductService().createProduct(newproduct);
        inputProduct.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "Producto guardado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), NewProductActivity.class));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getAllInputs() {
        inputName = new ArrayList<>();
        final Context context = this;

        inputName.add("unidades");
        inputName.add("litros");
        inputName.add("centimetros");
        inputName.add("metros");
        inputName.add("rollo");
        inputName.add("galones");
        inputName.add("litros");
        inputName.add("kilos");
        inputName.add("gramos");

        ArrayAdapter<String> adapterInput = new ArrayAdapter<>( context,android.R.layout.simple_spinner_item,inputName);
        adapterInput.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_unit.setAdapter(adapterInput);

    }
}