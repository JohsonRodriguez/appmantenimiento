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
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmantenimiento.Dto.OuputDto;
import com.example.appmantenimiento.Dto.ProductBrandDto;
import com.example.appmantenimiento.Dto.ProductNameDto;
import com.example.appmantenimiento.Entity.Brand;
import com.example.appmantenimiento.Entity.Employee;
import com.example.appmantenimiento.Entity.Location;
import com.example.appmantenimiento.Entity.Output;
import com.example.appmantenimiento.Entity.Product;
import com.example.appmantenimiento.api.ApiClient;
import com.lazyprogrammer.motiontoast.MotionStyle;
import com.lazyprogrammer.motiontoast.MotionToast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutputActivity extends AppCompatActivity {
    ArrayList<String> employeeName, productsName, locationName,productsBrand;
    Spinner spinner_Employee,spinner_Product, spinner_location,spinner_Brand;
    float  amount;
    List<Long> listIdEmployee,  listIdLocation;
    ImageButton backMenu;
    EditText _amount;
    TextView unit;
    Button btnSend;
    String productName;
    String productBrand;
    String _users;
    String _unit;
    String employee;
    String location;
    String checkAmount;
    String rol;
    public static SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        employeeName=new ArrayList<>();
        productsName=new ArrayList<>();
        locationName=new ArrayList<>();
        productsBrand=new ArrayList<>();

        backMenu=findViewById(R.id.btn_atras);
        _amount=findViewById(R.id.edtAmountOut);
        btnSend=findViewById(R.id.btn_Save);
        unit=findViewById(R.id.et1_unit);
        spinner_Employee=findViewById(R.id.spinerPersonal);
        spinner_Product=findViewById(R.id.spinerProduct);
        spinner_location=findViewById(R.id.spinerUbicacion);
        spinner_Brand=findViewById(R.id.spinerBrandOut);

        getAllEmployees();
        getAllProducts();
        getAllLocations();
        preferences = getSharedPreferences(getPackageName()+ "_preferences", Context.MODE_PRIVATE);
        rol=preferences.getString("rol", "");

        //Button Back Menu
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


        //Select Employee
        spinner_Employee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                employee=spinner_Employee.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Select Product
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

        //Select Brand
        spinner_Brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                productBrand=(String) spinner_Brand.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Select Location
        spinner_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                location=spinner_location.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Button Save
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAmount= _amount.getText().toString();
                _users= "LEONARDO GUZMAN";
                if (checkAmount.isEmpty() ||checkAmount.equals("0") ) {
                    MotionToast motionToast =  new MotionToast(OutputActivity.this,0,
                            MotionStyle.LIGHT,
                            MotionStyle.WARNING,
                            MotionStyle.BOTTOM,
                            "ALERTA",
                            "Debe llenar cantidad de productos",
                            MotionStyle.LENGTH_SHORT).show();
                } else {
                    amount= Float.parseFloat(checkAmount);
                    sendAddOutput(amount,productName,productBrand,_users,employee,location,_unit);
                }
            }
        });

    }


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


    private void getAllEmployees() {
        employeeName = new ArrayList<>();
        listIdEmployee = new ArrayList<>();
        Call<List<Employee>> employeeList = ApiClient.getEmployeeService().getEmployees();
        final Context context = this;
        employeeList.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> employeeList, Response<List<Employee>> response) {
                for (Employee e : response.body()) {
                    employeeName.add(e.getName()+" "+ e.getLastname());
                    listIdEmployee.add(e.getId());

                }
                ArrayAdapter<String> adapterEmployee = new ArrayAdapter<>( context,android.R.layout.simple_spinner_item,employeeName);
                adapterEmployee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_Employee.setAdapter(adapterEmployee);

            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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
                spinner_Product.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getAllLocations() {
        locationName = new ArrayList<>();
        listIdLocation = new ArrayList<>();
        Call<List<Location>> locationList = ApiClient.getLocationService().getLocations();
        final Context context = this;
        locationList.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> employeeList, Response<List<Location>> response) {
                for (Location l : response.body()) {
                    locationName.add(l.getName());
                    listIdLocation.add(l.getId());

                }
                ArrayAdapter<String> adapterLocation = new ArrayAdapter<>( context,android.R.layout.simple_spinner_item,locationName);
                adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_location.setAdapter(adapterLocation);

            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Send Data Output
    private void sendAddOutput(float amount, String productName, String productBrand, String users, String employee, String location, String _unit) {
        OuputDto output=new OuputDto();
        output.setAmount(amount);
        output.setProductName(productName);
        output.setProductBrand(productBrand);
        output.setUsers(users);
        output.setEmployee(employee);
        output.setLocation(location);
        output.setUnit(_unit);
        Call<Void> outputProduct= ApiClient.getOutputService().createOutput(output);
        outputProduct.enqueue(new Callback<Void>(){
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    MotionToast motionToast =  new MotionToast(OutputActivity.this,0,
                            MotionStyle.LIGHT,
                            MotionStyle.SUCCESS,
                            MotionStyle.BOTTOM,
                            "EXITO",
                            "Registro de salida guardado",
                            MotionStyle.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), OutputActivity.class));
                }else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());

                        MotionToast motionToastR = new MotionToast(OutputActivity.this, 0,
                                MotionStyle.LIGHT,
                                MotionStyle.ERROR,
                                MotionStyle.BOTTOM,
                                "ERROR",
                                jObjError.getString("details"),
                                MotionStyle.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        MotionToast motionToastR = new MotionToast(OutputActivity.this, 0,
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
                MotionToast motionToastR = new MotionToast(OutputActivity.this, 0,
                        MotionStyle.LIGHT,
                        MotionStyle.ERROR,
                        MotionStyle.BOTTOM,
                        "ERROR",
                        t.getMessage(),
                        MotionStyle.LENGTH_SHORT).show();
            }
        });


    }
}