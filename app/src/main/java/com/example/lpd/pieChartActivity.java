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

public class pieChartActivity extends AppCompatActivity {

    private Button button;
    private Button button3;
    private EditText editText;
    private EditText editText1;

    ArrayList<PieEntry> valueArrays = new ArrayList<>();


    ArrayList<Float> x = new ArrayList<Float>();
    ArrayList<String> y = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

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

                y.add(yvalue);


            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test1();
            }
        });

    }

    public void test1(){



        for (int i = 0; i < x.size(); i++){
            valueArrays.add(new PieEntry(x.get(i),y.get(i)));

        }

        PieDataSet dataSet = new PieDataSet(valueArrays, "Randomnes");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(dataSet);

        PieChart chart = (PieChart) findViewById(R.id.pieChart);
        chart.setData(data);
        chart.animateY(2000);
        chart.invalidate();





    }
}