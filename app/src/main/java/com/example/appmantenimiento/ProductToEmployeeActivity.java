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

import com.example.appmantenimiento.Dto.EmployeeTotalDto;
import com.example.appmantenimiento.Dto.OutputProductDto;
import com.example.appmantenimiento.Dto.OutputProductTotalDto;
import com.example.appmantenimiento.Entity.Product;
import com.example.appmantenimiento.adapter.OuputEmployeeAdapter;
import com.example.appmantenimiento.adapter.OutputProductAdapter;
import com.example.appmantenimiento.api.ApiClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductToEmployeeActivity extends AppCompatActivity {
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
    List<Long> listIdProduct;
    RecyclerView recyclerViewEmployee;
   OuputEmployeeAdapter ouputEmployeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_to_employee);
        productName=new ArrayList<>();
        spinner_product=findViewById(R.id.spinerEmployeeProduct);
        spinner_mount=findViewById(R.id.spinerMesEmployee);
        backMenu=findViewById(R.id.btn_backMenuEmployee);
        btnProcess=findViewById(R.id._btn_ProcessProEMp);

        recyclerViewEmployee=findViewById(R.id._rwProductToEmployee);
        recyclerViewEmployee.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployee.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        ouputEmployeeAdapter = new OuputEmployeeAdapter(this::ClickedEmployee);

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
                getDataTotalEmployee();
            }
        });
    }

    private void ClickedEmployee(EmployeeTotalDto employeeTotalDto) {
    }



    private void getDataTotalEmployee() {
         OutputProductDto outputProductDto = new OutputProductDto();
        outputProductDto.setProduct(idProductSelect);
        outputProductDto.setDate(date);
        Call<List<EmployeeTotalDto>> TotalEmployeeService= ApiClient.getOutputService().sumOutputEmployee(outputProductDto);
        TotalEmployeeService.enqueue(new Callback<List<EmployeeTotalDto>>() {
            @Override
            public void onResponse(Call<List<EmployeeTotalDto>> call, Response<List<EmployeeTotalDto>> response) {
                if(response.isSuccessful()){
                    List<EmployeeTotalDto> outputEmployeeTotalResponse = response.body();
                    if(!outputEmployeeTotalResponse.isEmpty() ) {
                        Log.e("ok", outputEmployeeTotalResponse.toString());
                        ouputEmployeeAdapter.setData(outputEmployeeTotalResponse);
                        recyclerViewEmployee.setAdapter(ouputEmployeeAdapter);
                    }else{
                        Toast.makeText(getApplicationContext(),"Aun no hay registros" , Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<EmployeeTotalDto>> call, Throwable t) {
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