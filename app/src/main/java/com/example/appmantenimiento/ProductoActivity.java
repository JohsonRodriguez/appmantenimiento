package com.example.appmantenimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class ProductoActivity extends AppCompatActivity {
     ImageButton btnMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        btnMenu=findViewById(R.id.btn_atras);
        btnMenu.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        });

    }
}