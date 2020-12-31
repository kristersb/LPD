package com.example.lpd;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    private Button button;
    private Button button3;
    private EditText editText;
    private EditText editText1;





    ArrayList<BarEntry> valueArray = new ArrayList<>();


    ArrayList<Float> x = new ArrayList<Float>();
    ArrayList<Float> y = new ArrayList<Float>();

    //float testing[] = {1f,2f};
    //float testing1[] = {2f,3f,4f,5f,7f};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);



        button = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        editText = (EditText) findViewById(R.id.editTextNumber);
        editText1 = (EditText) findViewById(R.id.editTextX);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String xvalue = editText1.getText().toString();
                float xFloat = Float.parseFloat(xvalue );
                x.add((float) xFloat);

                String yvalue = editText.getText().toString();
                float yFloat = Float.parseFloat(yvalue);
                y.add((float) yFloat);


            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });




    }

    public void test(){



        for (int i = 0; i < x.size(); i++){
            valueArray.add(new BarEntry(x.get(i),y.get(i)));

        }



        BarDataSet barDataSet = new BarDataSet(valueArray,"RandomNumbers");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        BarChart barChart = findViewById(R.id.barChart);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Testing");
        barChart.animateY(2000);

    }
}