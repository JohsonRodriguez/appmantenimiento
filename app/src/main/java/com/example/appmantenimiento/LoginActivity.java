package com.example.appmantenimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appmantenimiento.Dto.Login;
import com.example.appmantenimiento.Dto.ProductDto;
import com.example.appmantenimiento.Dto.UserDto;
import com.example.appmantenimiento.api.ApiClient;
import com.lazyprogrammer.motiontoast.MotionStyle;
import com.lazyprogrammer.motiontoast.MotionToast;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnlogin;
    EditText txtusername, txtpassword;
    String username,password,name, rol;
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnlogin=findViewById(R.id.btn_login);
        txtusername=findViewById(R.id.etusername);
        txtpassword=findViewById(R.id.etpassword);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=txtpassword.getText().toString().trim();;
                username=txtusername.getText().toString().trim();;
                if (username.isEmpty() ||password.isEmpty() ){
                    MotionToast motionToast =  new MotionToast(LoginActivity.this,0,
                            MotionStyle.LIGHT,
                            MotionStyle.WARNING,
                            MotionStyle.BOTTOM,
                            "ALERTA",
                            "Debe llenar todos los campos",
                            MotionStyle.LENGTH_SHORT).show();
                }else{
                    login(username,password);
                }
            }
        });
    }

    private void login(String username, String password) {
        Login login=new Login();
        login.setUsername(username);
        login.setPassword(password);
        Call<UserDto> user= ApiClient.getUserService().login(login);
        user.enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                if (response.isSuccessful()){
                    name=response.body().getName();
                    rol=response.body().getRol();
                    preferences = getSharedPreferences(getPackageName()+ "_preferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("user", name);
                    editor.putString("rol", rol);
                    editor.apply();

                    MotionToast motionToast =  new MotionToast(LoginActivity.this,0,
                            MotionStyle.LIGHT,
                            MotionStyle.SUCCESS,
                            MotionStyle.BOTTOM,
                            "EXITO",
                            "Inicio de sesión correcto",
                            MotionStyle.LENGTH_SHORT).show();
                    if(rol.equals("ADMIN")){
                        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                    }else{
                        startActivity(new Intent(getApplicationContext(), MenuUserActivity.class));
                    }

                }else{
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());

                        MotionToast motionToastR =  new MotionToast(LoginActivity.this,0,
                                MotionStyle.LIGHT,
                                MotionStyle.ERROR,
                                MotionStyle.BOTTOM,
                                "ERROR",
                                jObjError.getString("details"),
                                MotionStyle.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        MotionToast motionToastR =  new MotionToast(LoginActivity.this,0,
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
            public void onFailure(Call<UserDto> call, Throwable t) {
                t.printStackTrace();
                MotionToast motionToastR =  new MotionToast(LoginActivity.this,0,
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