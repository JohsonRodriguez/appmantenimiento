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
import com.example.appmantenimiento.Dto.ProductMonth;
import com.example.appmantenimiento.Entity.Product;
import com.example.appmantenimiento.adapter.OutputProductAdapter;
import com.example.appmantenimiento.adapter.ProductMonthAdapter;
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

public class ProductByMonthActivity extends AppCompatActivity {
    //Initialize variable array
    ArrayList<String> mounthName;
    //Initialize spinner
    Spinner spinner_mount;
    ImageButton backMenu;
    Button btnProcess;
    //Initialize varible
    String date, year, mounth,productName,rol,monthselected;
    RecyclerView rwProductMonth;
    ProductMonthAdapter productMonthAdapter;
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_month);
        spinner_mount=findViewById(R.id.spinerMesProduct);
        backMenu=findViewById(R.id.btn_backProductMonth);
        btnProcess=findViewById(R.id._btnProductMonth);

        rwProductMonth=findViewById(R.id.rwProductByMes);
        rwProductMonth.setLayoutManager(new LinearLayoutManager(this));
        rwProductMonth.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        productMonthAdapter = new ProductMonthAdapter(this::ClickedProductMonth);

        //Call Method
        getYear();
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

    private void ClickedProductMonth(ProductMonth productMonth) {
    }



    private void getDataTotalProduct() {
        Call<List<ProductMonth>> TotalProduct= ApiClient.getOutputService().getAllProductsByMonth(date);
        TotalProduct.enqueue(new Callback<List<ProductMonth>>() {
            @Override
            public void onResponse(Call<List<ProductMonth>> call, Response<List<ProductMonth>> response) {
                if(response.isSuccessful()){
                    List<ProductMonth> outputProductMonthResponse = response.body();
                    if(!outputProductMonthResponse.isEmpty()){
                        productMonthAdapter.setData(outputProductMonthResponse);
                        rwProductMonth.setAdapter(productMonthAdapter);
                    }else{
                        MotionToast motionToast =  new MotionToast(ProductByMonthActivity.this,0,
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
            public void onFailure(Call<List<ProductMonth>> call, Throwable t) {
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


}