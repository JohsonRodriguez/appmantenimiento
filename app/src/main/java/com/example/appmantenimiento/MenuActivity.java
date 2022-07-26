package com.example.appmantenimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button btn_ingProd, btn_stock, btn_SalProduct, btn_Location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_stock=findViewById(R.id.btn_rInventario);
        btn_ingProd=findViewById(R.id.btn_IngProducto);
        btn_SalProduct=findViewById(R.id.btn_SalProducto);
        btn_Location=findViewById(R.id.btn_report);

        btn_Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProductToLocationActivity.class));
            }
        });

        btn_stock.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), StockActivity.class));
        });

        btn_ingProd.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), ProductoActivity.class));
        });

        btn_SalProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OutputActivity.class));
            }
        });
    }
}