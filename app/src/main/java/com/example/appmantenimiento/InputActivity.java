package com.example.appmantenimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.lazyprogrammer.motiontoast.MotionStyle;
import com.lazyprogrammer.motiontoast.MotionToast;

import org.json.JSONObject;

public class InputActivity extends AppCompatActivity {
    ArrayList<String> productsName,productsBrand;
    public static SharedPreferences preferences;
    Spinner spinner_Product,spinner_Brand;
    TextView unit;
    ImageButton btnMenu;
    Button btnAdd;
    EditText _edtAmount;
    String productName, productBrand,_users,_unit, checkAmount,rol;
    float _amount;




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
        preferences = getSharedPreferences(getPackageName()+ "_preferences", Context.MODE_PRIVATE);
        rol=preferences.getString("rol", "");

        //Button Back
        btnMenu.setOnClickListener(v->{
            if(rol.equals("ADMIN")){
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }else{
                startActivity(new Intent(getApplicationContext(), MenuUserActivity.class));
            }
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
                checkAmount= _edtAmount.getText().toString();
                _users= "LEONARDO GUZMAN";
                if (checkAmount.isEmpty() ||checkAmount.equals("0") ) {
                    MotionToast motionToast =  new MotionToast(InputActivity.this,0,
                            MotionStyle.LIGHT,
                            MotionStyle.WARNING,
                            MotionStyle.BOTTOM,
                            "ALERTA",
                            "Debe llenar cantidad de productos",
                            MotionStyle.LENGTH_SHORT).show();

                } else {
                    _amount= Float.parseFloat(checkAmount);
                    sendAddProduct(_amount,productName,productBrand,_users,_unit);
                }

            }

            private void sendAddProduct(float amount, String productName, String productBrand, String users, String _unit) {
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
                        if (response.isSuccessful()){
                            MotionToast motionToast =  new MotionToast(InputActivity.this,0,
                                    MotionStyle.LIGHT,
                                    MotionStyle.SUCCESS,
                                    MotionStyle.BOTTOM,
                                    "EXITO",
                                    "Registro de ingreso guardado",
                                    MotionStyle.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), InputActivity.class));
                        }else {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());

                                MotionToast motionToastR = new MotionToast(InputActivity.this, 0,
                                        MotionStyle.LIGHT,
                                        MotionStyle.ERROR,
                                        MotionStyle.BOTTOM,
                                        "ERROR",
                                        jObjError.getString("details"),
                                        MotionStyle.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                MotionToast motionToastR = new MotionToast(InputActivity.this, 0,
                                        MotionStyle.LIGHT,
                                        MotionStyle.ERROR,
                                        MotionStyle.BOTTOM,
                                        "ERROR",
                                        "Ocurrio un error inesperado",
                                        MotionStyle.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                        MotionToast motionToastR = new MotionToast(InputActivity.this, 0,
                                MotionStyle.LIGHT,
                                MotionStyle.ERROR,
                                MotionStyle.BOTTOM,
                                "ERROR",
                                t.getMessage(),
                                MotionStyle.LENGTH_SHORT).show();

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
               t.printStackTrace();
               Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

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
               t.printStackTrace();
               Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });

    }

}
