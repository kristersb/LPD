package com.example.lpd;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class LineChartActivity extends AppCompatActivity {

    LineChart lineChart;
    LineData lineData;
    LineDataSet lineDataSet;
    ArrayList<Entry> lineChartValueArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        lineDataSet = new LineDataSet(lineChartValueArray, "");
        lineDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(16f);
        lineData = new LineData(lineDataSet);
        lineChart = findViewById(R.id.lineChart);
        lineChart.setData(new LineData(lineDataSet));
        lineChart.getDescription().setText("");
        lineChart.invalidate();

        Button buttonAdd = findViewById(R.id.buttonAdd);
        EditText textPositionNumber = findViewById(R.id.textPositionNumber);
        EditText textValue = findViewById(R.id.textValue);

        buttonAdd.setOnClickListener(v -> {

            float value = Float.parseFloat(textValue.getText().toString());
            float position = Float.parseFloat(textPositionNumber.getText().toString());
            lineChartValueArray.add(new Entry(position, value));
            lineDataSet.setValues(lineChartValueArray);
            lineChart.setData(new LineData(lineDataSet));
            lineChart.invalidate();
        });
    }
}
