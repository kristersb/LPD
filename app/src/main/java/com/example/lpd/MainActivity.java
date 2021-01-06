package com.example.lpd;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonPieChart, buttonBarChart, buttonLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPieChart = findViewById(R.id.buttonPieChart);
        buttonBarChart = findViewById(R.id.buttonBarChart);
        buttonLineChart = findViewById(R.id.buttonLineChart);

        buttonPieChart.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), PieChartActivity.class)));

        buttonBarChart.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), BarChartActivity.class)));

        buttonLineChart.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), LineChartActivity.class)));

    }
}