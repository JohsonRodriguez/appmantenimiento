package com.example.appmantenimiento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appmantenimiento.Dto.AllInputDto;
import com.example.appmantenimiento.Entity.Input;
import com.example.appmantenimiento.adapter.AllInputAdapter;
import com.example.appmantenimiento.api.ApiClient;
import com.lazyprogrammer.motiontoast.MotionStyle;
import com.lazyprogrammer.motiontoast.MotionToast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputRegistersActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private ImageButton btnBack;
    RecyclerView recyclerView;
    String today,rol;
    AllInputAdapter allInputAdapter;
    public static SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_registers);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButtonInput);
        dateButton.setText(getTodaysDate());
        recyclerView=findViewById(R.id.rwInputs);
        btnBack=findViewById(R.id.btn_backMenu2);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        allInputAdapter = new AllInputAdapter(this::ClickedInputs);
        getToday();
        getAllRegisters(today);
        preferences = getSharedPreferences(getPackageName()+ "_preferences", Context.MODE_PRIVATE);
        rol=preferences.getString("rol", "");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rol.equals("ADMIN")){
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                }else{
                    startActivity(new Intent(getApplicationContext(), MenuUserActivity.class));
                }
            }
        });
    }

    private void ClickedInputs(AllInputAdapter allInputAdapter) {
    }

    private void getAllRegisters(String today) {
        Call<List<Input>> allInputs = ApiClient.getInputService().getAllInputs(today);
        allInputs.enqueue(new Callback<List<Input>>() {
            @Override
            public void onResponse(Call<List<Input>> call, Response<List<Input>> response) {
                if (response.isSuccessful()){
                    List<Input> inputs =response.body();
                    if (!inputs.isEmpty()){
                        allInputAdapter.setData(inputs);
                        recyclerView.setAdapter(allInputAdapter);
                    }else{
                        MotionToast motionToast =  new MotionToast(InputRegistersActivity.this,0,
                                MotionStyle.LIGHT,
                                MotionStyle.WARNING,
                                MotionStyle.BOTTOM,
                                "ALERTA",
                                "No hay registros en este día",
                                MotionStyle.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Input>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public String getToday(){
        long ahora = System.currentTimeMillis();
        Date fecha = new Date(ahora);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return  today = df.format(fecha);


    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                String date2 = makeDateString2(year, month,day );
                Log.d("today is  ", date2);
                getAllRegisters(date2);
//                startActivity(new Intent(getApplicationContext(), ShowRegister.class));
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }
    private String makeDateString2(int year, int month, int day)
    {
        return year + "-" +getMonthFormat2(month) + "-" + day  ;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "ENE";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "ABR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AGO";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }
    private String getMonthFormat2(int month)
    {
        if(month == 1)
            return "01";
        if(month == 2)
            return "02";
        if(month == 3)
            return "03";
        if(month == 4)
            return "04";
        if(month == 5)
            return "05";
        if(month == 6)
            return "06";
        if(month == 7)
            return "07";
        if(month == 8)
            return "08";
        if(month == 9)
            return "09";
        if(month == 10)
            return "10";
        if(month == 11)
            return "11";
        if(month == 12)
            return "12";

        //default should never happen
        return "01";
    }
    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

}