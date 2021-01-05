package com.example.lpd;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    private EditText textPositionNumber, textValue;
    BarDataSet barDataSet;
    BarData barData;
    BarChart barChart;

    ArrayList<BarEntry> chartValueArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        textPositionNumber = findViewById(R.id.textPositionNumber);
        textValue = findViewById(R.id.textValue);

        barDataSet = new BarDataSet(chartValueArray,"");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barData = new BarData(barDataSet);
        barChart = findViewById(R.id.barChart);
        barChart.setData(new BarData(barDataSet));
        barChart.getDescription().setText("");
        barChart.invalidate();

        buttonAdd.setOnClickListener(v -> {

            float xFloat = Float.parseFloat(textPositionNumber.getText().toString());
            float yFloat = Float.parseFloat(textValue.getText().toString());
            chartValueArray.add(new BarEntry(xFloat, yFloat));
            barDataSet.setValues(chartValueArray);
            barChart.setData(new BarData(barDataSet));
            barChart.invalidate();

        });

    }

}