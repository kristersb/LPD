package com.example.lpd;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

public class LineChartActivity extends AppCompatActivity {

    LineChart lineChart;
    LineDataSet lineDataSet;
    ArrayList<Entry> lineDataArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);


        Button buttonEdit = findViewById(R.id.buttonEdit);

        buttonEdit.setOnClickListener(v -> {
            Intent intent = new Intent(this, BarSheet.class);
            ArrayList<Float> valueArrayListFloat = new ArrayList<>();
            ArrayList<Integer> valueArrayListInteger = new ArrayList<>();
            for (int i = 0; i < lineDataArray.size(); i++) {
                valueArrayListInteger.add((int)lineDataArray.get(i).getX());
                valueArrayListFloat.add(lineDataArray.get(i).getY());
            }
            intent.putExtra("arrayFloat", valueArrayListFloat);
            intent.putIntegerArrayListExtra("arrayInteger", valueArrayListInteger);
            startActivityForResult(intent, 1);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ArrayList<Float> valueArrayFloat = (ArrayList<Float>)data.getSerializableExtra("arrayFloat");
            ArrayList<Integer> valueArrayInteger = data.getIntegerArrayListExtra("arrayInteger");
            SortedMap<Integer, ArrayList<Float>> sortingMap = new TreeMap<Integer, ArrayList<Float>>();

            for (int i = 0; i < valueArrayFloat.size(); i++) {
                ArrayList<Float> floatArrayNew = new ArrayList<>();
                floatArrayNew.add(valueArrayFloat.get(i));
                ArrayList<Float> floatArray = sortingMap.putIfAbsent(valueArrayInteger.get(i), floatArrayNew);
                if (floatArray != null) {
                    floatArray.add(valueArrayFloat.get(i));
                    sortingMap.put(valueArrayInteger.get(i), floatArray);
                }
            }
            lineDataArray.clear();
            while (!sortingMap.isEmpty()) {
                int key = sortingMap.firstKey();
                ArrayList<Float> valueArray = sortingMap.get(key);
                valueArray.sort(Collections.reverseOrder());
                for (int j = 0; j < valueArray.size(); j++) {
                    lineDataArray.add(new BarEntry(key, valueArray.get(j)));
                }
                sortingMap.remove(key);
            }

            if (!lineDataArray.isEmpty()) {
                lineDataSet = new LineDataSet(lineDataArray, "");
                lineDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                lineDataSet.setValueTextColor(Color.BLACK);
                lineDataSet.setValueTextSize(16f);
                lineChart = findViewById(R.id.lineChart);
                lineChart.getDescription().setText("");
                lineChart.setData(new LineData(lineDataSet));
                lineChart.setViewPortOffsets(40, 45, 40, 30);
                lineChart.animateY(1500, Easing.EaseOutCirc);
            } else {
                lineChart = findViewById(R.id.lineChart);
                lineChart.setData(null);
            }
        }
    }
}
