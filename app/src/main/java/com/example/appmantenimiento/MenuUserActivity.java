package com.example.appmantenimiento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuUserActivity extends AppCompatActivity {
    public static SharedPreferences preferences;
    TextView user;
    CardView btn_stock,btn_Location, btn_Employee, btn_AllInput,btn_AllOutputs,btn_Logout,btn_month;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user);
        btn_stock=findViewById(R.id.btn_rInventario);
        btn_Location=findViewById(R.id.btn_report);
        btn_Employee=findViewById(R.id.btn_reportPersonal);
        btn_AllInput=findViewById(R.id.btn_Inputs);
        btn_AllOutputs=findViewById(R.id.btn_Outputs);
        btn_Logout=findViewById(R.id.btn_logout);
        btn_month=findViewById(R.id.btn_Month);
        user=findViewById(R.id.txtnameuser);
        preferences = getSharedPreferences(getPackageName()+ "_preferences", Context.MODE_PRIVATE);
        name=preferences.getString("user", "");
        user.setText("Hola " + name);

        btn_AllOutputs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AllOutputsActivity.class));

            }
        });

        btn_AllInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), InputRegistersActivity.class));
            }
        });
        btn_Employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProductToEmployeeActivity.class));
            }
        });
        btn_Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProductToLocationActivity.class));
            }
        });
        btn_stock.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), StockActivity.class));
        });
        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
        btn_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProductByMonthActivity.class));
            }
        });
    }
}