package com.example.appmantenimiento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.appmantenimiento.adapter.ProductAdapter;
import com.example.appmantenimiento.api.ApiClient;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductToLocationActivity extends AppCompatActivity {
    //Initialize variable array
    ArrayList<String> productName, mounthName;
    //Initialize spinner
    Spinner spinner_product, spinner_mount;
    ImageButton backMenu;
    Button btnProcess;
    //Initialize varible
    Long idProductSelect;
    String date, year, mounth;
    //Initializa List variable
    List<Long> listIdMounth, listIdProduct, listIdLocation;
    RecyclerView recyclerViewLocation;
    OutputProductAdapter outputProductAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_to_location);
        productName=new ArrayList<>();
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

        // click Buttom Back
        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MenuActivity.class));
            }
        });

        //Clic Spinner Product
        spinner_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int positionSpinner= spinner_product.getSelectedItemPosition();
                idProductSelect = listIdProduct.get(positionSpinner);
                Toast.makeText(getApplicationContext(), idProductSelect.toString(), Toast.LENGTH_SHORT).show();
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

                Toast.makeText(getApplicationContext(), "el mes es: " + date, Toast.LENGTH_SHORT).show();
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
        outputProductDto.setProduct(idProductSelect);
        outputProductDto.setDate(date);
        Call<List<OutputProductTotalDto>>TotalProductService=ApiClient.getOutputService().sumOutput(outputProductDto);
        TotalProductService.enqueue(new Callback<List<OutputProductTotalDto>>() {
            @Override
            public void onResponse(Call<List<OutputProductTotalDto>> call, Response<List<OutputProductTotalDto>> response) {
                if(response.isSuccessful()){
                    List<OutputProductTotalDto> outputProductTotalResponse = response.body();
                    Log.e("ok", outputProductTotalResponse.toString());
                    outputProductAdapter.setData(outputProductTotalResponse);
                    recyclerViewLocation.setAdapter(outputProductAdapter);
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
        productName = new ArrayList<>();
        listIdProduct = new ArrayList<>();
        Call<List<Product>> productlist = ApiClient.getProductsService().getProducts();
        final Context context = this;
        productlist.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> productlist, Response<List<Product>> response) {
                for (Product p : response.body()) {
                    productName.add(p.getName());
                    listIdProduct.add(p.getId());

                }
                ArrayAdapter<String> adapterProduct = new ArrayAdapter<>( context,android.R.layout.simple_spinner_item,productName);
                adapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_product.setAdapter(adapterProduct);

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}