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
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appmantenimiento.Entity.Input;
import com.example.appmantenimiento.Entity.Product;
import com.example.appmantenimiento.api.ApiClient;

public class ProductoActivity extends AppCompatActivity {
    ArrayList<String> productsName;
    Spinner spinner_Product;
    ImageButton btnMenu;
    Button btnAdd;
    EditText _edtAmount;
    Long _amount,_users, idProductSelect;
    List<Long> listIdProduct;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        btnMenu=findViewById(R.id.btn_atras);
        btnAdd=findViewById(R.id.btn_Add);
        _edtAmount=findViewById(R.id.edtAmount);
        productsName=new ArrayList<>();
        spinner_Product=findViewById(R.id.spinerProducts);
        getAllProducts();

        //Button Back
        btnMenu.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        });
       //Select Spinner
        spinner_Product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String productName=(String) spinner_Product.getSelectedItem();
                int positionSpinner= spinner_Product.getSelectedItemPosition();
                 idProductSelect = listIdProduct.get(positionSpinner);
//                searchProduct(productName);
//               Toast.makeText(getApplicationContext(), idProductSelect.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Button Agregar
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _amount= Long.valueOf(_edtAmount.getText().toString());
                _users= 1L;
                sendAddProduct(_amount,idProductSelect,_users);
            }

            private void sendAddProduct(Long amount, Long idproduct, Long users) {
                Input iproduct= new Input();
                iproduct.setProduct(idproduct);
                iproduct.setAmount(amount);
                iproduct.setUsers(users);
                Call<Void> inputProduct= ApiClient.getProductsService().createInput(iproduct);
                Log.i("output",inputProduct.toString());
                inputProduct.enqueue(new Callback<Void>(){
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getApplicationContext(), "Producto guardado", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(getApplicationContext(), ProductoActivity.class));
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
            });
        }
    });}

//    public void searchProduct(String productName) {
//        Call<Product> productSelected= ApiClient.getProductsService().getOneProduct(productName);
//        Log.i("output",productSelected.toString());
//        productSelected.enqueue(new Callback<Product>(){
//            @Override
//            public void onResponse(Call<Product> call, Response<Product> response) {
//                Product p = (Product) response.body();
//                _idproduct = Long.valueOf(p.getId().toString()) ;
//                Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Product> call, Throwable t) {
//                t.printStackTrace();
//                Log.i("output error",t.getMessage());
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//
//        });
//
//    }

    public void getAllProducts() {
        productsName = new ArrayList<>();
        listIdProduct = new ArrayList<>();
        Call<List<Product>> productlist = ApiClient.getProductsService().getProducts();
        final Context context = this;
        productlist.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> productlist, Response<List<Product>> response) {
                for (Product p : response.body()) {
                    productsName.add(p.getName());
                    listIdProduct.add(p.getId());

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

}
