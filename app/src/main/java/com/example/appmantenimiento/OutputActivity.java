package com.example.appmantenimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appmantenimiento.Entity.Employee;
import com.example.appmantenimiento.Entity.Product;
import com.example.appmantenimiento.api.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutputActivity extends AppCompatActivity {
    ArrayList<String> employeeName;
    Spinner spinner_Employee;
    Long idEmployeeSelect;
    List<Long> listIdEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        employeeName=new ArrayList<>();
        spinner_Employee=findViewById(R.id.spinerPersonal);
        getAllEmployees();

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
                ArrayAdapter<String> adapter = new ArrayAdapter<>( context,android.R.layout.simple_spinner_item,employeeName);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_Employee.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}