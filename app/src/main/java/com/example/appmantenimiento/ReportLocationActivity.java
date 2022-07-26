package com.example.appmantenimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ReportLocationActivity extends AppCompatActivity {
    // Initialize variable
    BarChart barchart;
    PieChart piechart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_location);

        //Assign variable
        barchart= findViewById(R.id.bar_chart);
        piechart=findViewById(R.id.pie_chart);

        // Initizable array list
        ArrayList<BarEntry> barEntries= new ArrayList<>();

        ArrayList<PieEntry> pieEntries= new ArrayList<>();
        //Use for loop
        for(int i=1; i<10; i++){
            //Convert to float
            float value= (float) (i*10.0);
            //Initialize bar chart entry
            BarEntry barEntry=new BarEntry(i,value);
            //Initialize pie chart entry
            PieEntry pieEntry = new PieEntry(i,value);
            //Add values in array list
            barEntries.add(barEntry);
           // pieEntries.add(pieEntry);
        }
        //Initialize bar chart entry

        pieEntries.add(new PieEntry(10,"BHPA"));
        pieEntries.add(new PieEntry(11,"BHPB"));
        pieEntries.add(new PieEntry(12,"BHPC"));
        pieEntries.add(new PieEntry(13,"BHPD"));
        pieEntries.add(new PieEntry(14,"BHPE"));
        pieEntries.add(new PieEntry(15,"BHPF"));
        pieEntries.add(new PieEntry(16,"BHPG"));
        pieEntries.add(new PieEntry(17,"BHPH"));
        pieEntries.add(new PieEntry(18,"BHPI"));
        pieEntries.add(new PieEntry(19,"BHPJ"));
        pieEntries.add(new PieEntry(11,"BHPK"));
        pieEntries.add(new PieEntry(12,"BHPL"));
        pieEntries.add(new PieEntry(13,"BHPLL"));
        pieEntries.add(new PieEntry(14,"BHPM"));
        pieEntries.add(new PieEntry(15,"BHPN"));
        pieEntries.add(new PieEntry(16,"BHPO"));
        pieEntries.add(new PieEntry(17,"BHPP"));
        pieEntries.add(new PieEntry(18,"BHPQ"));
        pieEntries.add(new PieEntry(19,"BHPR"));



        // Initialize bar data set
        BarDataSet barDataSet= new BarDataSet(barEntries,"Employees");
        //Set colors
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //Hide draw value
        barDataSet.setDrawValues(false);

        // Set bar data
        barchart.setData(new BarData((barDataSet)));
        //Set animation
        barchart.animateY(5000);
        //Set description text and color
        barchart.getDescription().setText("Employees Chart");
        barchart.getDescription().setTextColor(Color.BLUE);
        //Initialize pie data set
        PieDataSet pieDataSet = new PieDataSet(pieEntries,"Baños");
        //Set colors
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //Set pie data
        piechart.setData(new PieData(pieDataSet));
        //Set animation
        piechart.animateXY(1000,1000);
        //Hide description
        piechart.getDescription().setEnabled(false);
    }
}