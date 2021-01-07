package com.example.lpd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    PieDataSet dataSet;
    PieData pieData;
    PieChart pieChart;
    ArrayList<PieEntry> pieDataArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        dataSet = new PieDataSet(pieDataArray, "");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieData = new PieData(dataSet);
        pieData.setValueTextSize(15f);
        Button buttonEdit = findViewById(R.id.buttonEdit);

        buttonEdit.setOnClickListener(v -> {
            Intent intent = new Intent(this, PieSheet.class);
            ArrayList<Float> valueArrayListFloat = new ArrayList<>();
            ArrayList<String> valueArrayListString = new ArrayList<>();
            for (int i = 0; i < pieDataArray.size(); i++) {
                valueArrayListString.add(pieDataArray.get(i).getLabel());
                valueArrayListFloat.add(pieDataArray.get(i).getValue());
            }
            intent.putExtra("arrayFloat", valueArrayListFloat);
            intent.putStringArrayListExtra("arrayString", valueArrayListString);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode  == RESULT_OK) {
            ArrayList<Float> valueArrayFloat = (ArrayList<Float>)data.getSerializableExtra("arrayFloat");
            ArrayList<String> valueArrayString = data.getStringArrayListExtra("arrayString");
            pieDataArray.clear();
            for (int i = 0; i < valueArrayFloat.size(); i++) {
                pieDataArray.add(new PieEntry(valueArrayFloat.get(i), valueArrayString.get(i)));
            }
            if (pieDataArray.isEmpty()) {
                pieChart = findViewById(R.id.pieChart);
                pieChart.setData(null);
            } else {
                pieChart = findViewById(R.id.pieChart);
                pieChart.setData(new PieData(dataSet));
                pieChart.getDescription().setText("");
                pieChart.animateY(1500);
            }
        }

    }

}