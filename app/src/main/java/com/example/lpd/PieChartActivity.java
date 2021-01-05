package com.example.lpd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    private Button buttonAdd, buttonBack;
    private EditText textName, textValue;
    PieDataSet dataSet;
    PieData pieData;
    PieChart pieChart;

    ArrayList<PieEntry> valueArrays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        dataSet = new PieDataSet(valueArrays, "");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieData = new PieData(dataSet);
        pieChart = findViewById(R.id.pieChart);
        pieChart.setData(new PieData(dataSet));
        pieChart.getDescription().setText("");
        pieChart.animateY(2000);
        pieChart.invalidate();

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        textName = (EditText) findViewById(R.id.textName);
        textValue = (EditText) findViewById(R.id.textValue);


        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                float value = Float.parseFloat(textValue.getText().toString());
                String name = textName.getText().toString();
                valueArrays.add(new PieEntry(value, name));
                pieChart.setData(pieData);
                pieChart.invalidate();
            }
        });

    }

}