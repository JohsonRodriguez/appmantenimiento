package com.example.appmantenimiento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appmantenimiento.Dto.OutputProductDto;
import com.example.appmantenimiento.Dto.OutputProductTotalDto;
import com.example.appmantenimiento.Entity.Product;
import com.example.appmantenimiento.adapter.OutputProductAdapter;
import com.example.appmantenimiento.api.ApiClient;
import com.lazyprogrammer.motiontoast.MotionStyle;
import com.lazyprogrammer.motiontoast.MotionToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductToLocationActivity extends AppCompatActivity {
    //Initialize variable array
    ArrayList<String>  mounthName;
    ArrayList<String> productsName;
    //Initialize spinner
    Spinner spinner_product, spinner_mount;
    ImageButton backMenu;
    Button btnProcess;
    //Initialize varible
    Long idProductSelect;
    String date, year, mounth,productName,rol;
    RecyclerView recyclerViewLocation;
    OutputProductAdapter outputProductAdapter;
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_to_location);
        productsName=new ArrayList<>();
        spinner_product=findViewById(R.id.spinerLocationProduct);
        spinner_mount=findViewById(R.id.spinerMes);
        backMenu=findViewById(R.id.btn_backOutProduct);
        btnProcess=findViewById(R.id._btn_Process);

        recyclerViewLocation=findViewById(R.id.rwProductToLocation);
        recyclerViewLocation.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewLocation.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        outputProductAdapter = new OutputProductAdapter(this::ClickedProductTotal);

        //Call Method
        getYear();
        getAllProducts();
        getAllMonths();
        preferences = getSharedPreferences(getPackageName()+ "_preferences", Context.MODE_PRIVATE);
        rol=preferences.getString("rol", "");

        // click Buttom Back
        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rol.equals("ADMIN")){
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                }else{
                    startActivity(new Intent(getApplicationContext(), MenuUserActivity.class));
                }
            }
        });

        //Clic Spinner Product
        spinner_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                productName=(String) spinner_product.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //CLic Spinner Mounth
        spinner_mount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int positionSpinner= spinner_mount.getSelectedItemPosition();
                int realposition=positionSpinner+1;
                if (realposition<10){
                    mounth="0"+ realposition;
                }else
                    mounth= String.valueOf(realposition);
                date=year+"-"+mounth;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Button process click
        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataTotalProduct();
            }
        });
    }

    private void ClickedProductTotal(OutputProductTotalDto outputProductTotalDto) {
    }



    private void getDataTotalProduct() {
        OutputProductDto outputProductDto = new OutputProductDto();
        outputProductDto.setProduct(productName);
        outputProductDto.setDate(date);
        Call<List<OutputProductTotalDto>>TotalProductService=ApiClient.getOutputService().sumOutput(outputProductDto);
        TotalProductService.enqueue(new Callback<List<OutputProductTotalDto>>() {
            @Override
            public void onResponse(Call<List<OutputProductTotalDto>> call, Response<List<OutputProductTotalDto>> response) {
                if(response.isSuccessful()){
                    List<OutputProductTotalDto> outputProductTotalResponse = response.body();
                    if(!outputProductTotalResponse.isEmpty()){
                        outputProductAdapter.setData(outputProductTotalResponse);
                        recyclerViewLocation.setAdapter(outputProductAdapter);
                    }else{
                        MotionToast motionToast =  new MotionToast(ProductToLocationActivity.this,0,
                                MotionStyle.LIGHT,
                                MotionStyle.WARNING,
                                MotionStyle.BOTTOM,
                                "ALERTA",
                                "No hay registros en este mes",
                                MotionStyle.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<OutputProductTotalDto>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getYear() {
        Date date = new Date();
        SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
        year = getYearFormat.format(date);
    }

    private void getAllMonths() {
        mounthName = new ArrayList<>();
        final Context context = this;

        mounthName.add("ENERO");
        mounthName.add("FEBRERO");
        mounthName.add("MARZO");
        mounthName.add("ABRIL");
        mounthName.add("MAYO");
        mounthName.add("JUNIO");
        mounthName.add("JULIO");
        mounthName.add("AGOSTO");
        mounthName.add("SETIEMBRE");
        mounthName.add("OCTUBRE");
        mounthName.add("NOVIEMBRE");
        mounthName.add("DICIEMBRE");

        ArrayAdapter<String> adapterMounth = new ArrayAdapter<>( context,android.R.layout.simple_spinner_item,mounthName);
        adapterMounth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_mount.setAdapter(adapterMounth);

    }

    private void getAllProducts() {
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
                spinner_product.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}