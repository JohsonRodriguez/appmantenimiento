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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appmantenimiento.Dto.ProductDto;
import com.example.appmantenimiento.api.ApiClient;
import com.lazyprogrammer.motiontoast.MotionStyle;
import com.lazyprogrammer.motiontoast.MotionToast;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
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
    String product, brand, unit,rol;
    public static SharedPreferences preferences;
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
        preferences = getSharedPreferences(getPackageName()+ "_preferences", Context.MODE_PRIVATE);
        rol=preferences.getString("rol", "");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rol.equals("ADMIN")){
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                }else{
                    startActivity(new Intent(getApplicationContext(), MenuUserActivity.class));
                }
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
                if (product.isEmpty() ||brand.isEmpty() ){
                    MotionToast motionToast =  new MotionToast(NewProductActivity.this,0,
                            MotionStyle.LIGHT,
                            MotionStyle.WARNING,
                            MotionStyle.BOTTOM,
                            "ALERTA",
                            "Debe llenar todos los campos",
                            MotionStyle.LENGTH_SHORT).show();
                }else{
                    addProduct(product,brand,unit);
                }

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
                if (response.isSuccessful()){
                    MotionToast motionToast =  new MotionToast(NewProductActivity.this,0,
                            MotionStyle.LIGHT,
                            MotionStyle.SUCCESS,
                            MotionStyle.BOTTOM,
                            "EXITO",
                            "Producto guardado",
                            MotionStyle.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), NewProductActivity.class));
                }else{
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());

                        MotionToast motionToastR =  new MotionToast(NewProductActivity.this,0,
                                MotionStyle.LIGHT,
                                MotionStyle.ERROR,
                                MotionStyle.BOTTOM,
                                "ERROR",
                                jObjError.getString("details"),
                                MotionStyle.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        MotionToast motionToastR =  new MotionToast(NewProductActivity.this,0,
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
                MotionToast motionToastR =  new MotionToast(NewProductActivity.this,0,
                        MotionStyle.LIGHT,
                        MotionStyle.ERROR,
                        MotionStyle.BOTTOM,
                        "ERROR",
                        t.getMessage(),
                        MotionStyle.LENGTH_SHORT).show();


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