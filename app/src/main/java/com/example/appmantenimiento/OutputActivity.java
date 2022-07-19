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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appmantenimiento.Entity.Employee;
import com.example.appmantenimiento.Entity.Location;
import com.example.appmantenimiento.Entity.Output;
import com.example.appmantenimiento.Entity.Product;
import com.example.appmantenimiento.api.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutputActivity extends AppCompatActivity {
    ArrayList<String> employeeName, productName, locationName;
    Spinner spinner_Employee,spinner_Product, spinner_location;
    Long idEmployeeSelect, idProductSelect, idLocationSelect, amount,_users;
    List<Long> listIdEmployee, listIdProduct, listIdLocation;
    ImageButton backMenu;
    EditText _amount;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        employeeName=new ArrayList<>();
        productName=new ArrayList<>();
        locationName=new ArrayList<>();

        backMenu=findViewById(R.id.btn_atras);
        _amount=findViewById(R.id.edtAmountOut);
        btnSend=findViewById(R.id.btn_Save);

        spinner_Employee=findViewById(R.id.spinerPersonal);
        spinner_Product=findViewById(R.id.spinerProduct);
        spinner_location=findViewById(R.id.spinerUbicacion);

        getAllEmployees();
        getAllProducts();
        getAllLocations();

        //Button Back Menu
        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });


        //Select Employee
        spinner_Employee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int positionSpinner= spinner_Employee.getSelectedItemPosition();
                idEmployeeSelect = listIdEmployee.get(positionSpinner);
                Toast.makeText(getApplicationContext(), idEmployeeSelect.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Select Product
        spinner_Product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int positionSpinner= spinner_Product.getSelectedItemPosition();
                idProductSelect = listIdProduct.get(positionSpinner);
                Toast.makeText(getApplicationContext(), idProductSelect.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Select Location
        spinner_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int positionSpinner= spinner_location.getSelectedItemPosition();
                idLocationSelect = listIdLocation.get(positionSpinner);
                Toast.makeText(getApplicationContext(), idLocationSelect.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Button Save
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount= Long.valueOf(_amount.getText().toString());
                _users= 1L;
                sendAddOutput(amount,idProductSelect,_users,idEmployeeSelect,idLocationSelect);
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
                spinner_Product.setAdapter(adapterProduct);

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
    private void sendAddOutput(Long amount, Long idProductSelect, Long users, Long idEmployeeSelect, Long idLocationSelect) {
        Output output=new Output();
        output.setAmount(amount);
        output.setProduct(idProductSelect);
        output.setUsers(users);
        output.setEmployee(idEmployeeSelect);
        output.setLocation(idLocationSelect);
        Call<Void> outputProduct= ApiClient.getOutputService().createOutput(output);
        Log.i("output",outputProduct.toString());
        outputProduct.enqueue(new Callback<Void>(){
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "Se entrego el producto con exito", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), OutputActivity.class));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}