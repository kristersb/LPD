package com.example.lpd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;

public class BarChartActivity extends AppCompatActivity {

    BarDataSet barDataSet;
    BarChart barChart;
    ArrayList<BarEntry> barDataArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        Button buttonEdit = findViewById(R.id.buttonEdit);

        buttonEdit.setOnClickListener(v -> {
            Intent intent = new Intent(this, BarSheet.class);
            ArrayList<Float> valueArrayListFloat = new ArrayList<>();
            ArrayList<Integer> valueArrayListInteger = new ArrayList<>();
            for (int i = 0; i < barDataArray.size(); i++) {
                valueArrayListInteger.add((int)barDataArray.get(i).getX());
                valueArrayListFloat.add(barDataArray.get(i).getY());
            }
            intent.putExtra("arrayFloat", valueArrayListFloat);
            intent.putIntegerArrayListExtra("arrayInteger", valueArrayListInteger);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode  == RESULT_OK) {
            ArrayList<Float> valueArrayFloat = (ArrayList<Float>)data.getSerializableExtra("arrayFloat");
            ArrayList<Integer> valueArrayInteger = data.getIntegerArrayListExtra("arrayInteger");
            ArrayList<ArrayList<Float>> valueArrayArray = new ArrayList<>();
            ArrayList<Integer> positionArray = new ArrayList<>();

            for (int i = 0; i < valueArrayFloat.size(); i++) {
                boolean pass = true;
                for (int j = 0; j < positionArray.size(); j++) {
                    if (positionArray.get(j) == valueArrayInteger.get(i)) {
                        ArrayList<Float> valueArray = valueArrayArray.get(j);
                        valueArray.add(valueArrayFloat.get(i));
                        valueArrayArray.set(j, valueArray);
                        pass = false;
                        break;
                    }
                }
                if (pass) {
                    ArrayList<Float> valueArray = new ArrayList<>();
                    valueArray.add(valueArrayFloat.get(i));
                    valueArrayArray.add(valueArray);
                    positionArray.add(valueArrayInteger.get(i));
                }
            }

            barDataArray.clear();
            for (int i = 0; i < positionArray.size(); i++) {
                ArrayList<Float> floatList = valueArrayArray.get(i);
                floatList.sort(Collections.reverseOrder());
                float[] floatValues = new float[floatList.size()];
                for (int j = 0; j < floatValues.length; j++) {
                    barDataArray.add(new BarEntry(positionArray.get(i), floatList.get(j)));
                }
            }

            if (!barDataArray.isEmpty()) {
                barDataSet = new BarDataSet(barDataArray,"");
                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                barDataSet.setValueTextColor(Color.BLACK);
                barDataSet.setValueTextSize(16f);
                barChart = findViewById(R.id.barChart);
                barChart.getDescription().setText("");
                barChart.setData(new BarData(barDataSet));
                barChart.setViewPortOffsets(40, 45, 40, 30);
                barChart.animateY(1500, Easing.EaseOutCirc);
            } else {
                barChart = findViewById(R.id.barChart);
                barChart.setData(null);
            }
        }
    }
}