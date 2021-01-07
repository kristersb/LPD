package com.example.lpd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;


public class PieSheet extends AppCompatActivity {

    LinearLayout rowLayout;
    ArrayList<PieEntry> pieDataArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spread_sheet);

        Intent intent = getIntent();

        ArrayList<Float> valueArrayFloat = (ArrayList<Float>)intent.getSerializableExtra("arrayFloat");
        ArrayList<String> valueArrayString = intent.getStringArrayListExtra("arrayString");

        rowLayout = findViewById(R.id.rowLayout);
        for (int i = 0; i < 100; i++) {
            addRow(i, valueArrayFloat, valueArrayString);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        ArrayList<Float> valueArrayListFloat = new ArrayList<>();
        ArrayList<String> valueArrayListString = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            EditText textName = findViewById(1010000 + i);
            EditText textValue = findViewById(1020000 + i);
            String value = textValue.getText().toString();
            if (value.equals("")) {
                continue;
            }
            valueArrayListString.add(textName.getText().toString());
            valueArrayListFloat.add(Float.parseFloat(value));
        }
        Intent resultIntent = new Intent();
        resultIntent.putExtra("arrayFloat", valueArrayListFloat);
        resultIntent.putStringArrayListExtra("arrayString", valueArrayListString);
        setResult(RESULT_OK, resultIntent);
        finish();
    }


    @SuppressLint("SetTextI18n")
    private void addRow(int num, ArrayList<Float> valueArrayFloat, ArrayList<String> valueArrayString) {
        final View rowView = getLayoutInflater().inflate(R.layout.sheet_line, null, false);
        TextView textNum = rowView.findViewById(R.id.textNum);
        EditText textName = rowView.findViewById(R.id.textName);
        EditText textValue = rowView.findViewById(R.id.textValue);

        textNum.setText((num+1)+"");
        textName.setId(1010000 + num);
        textValue.setId(1020000 + num);
        if (num < valueArrayFloat.size()) {
            textName.setText(valueArrayString.get(num));
            textValue.setText(valueArrayFloat.get(num).toString());
        }

        pieDataArray.clear();
        for (int i = 0; i < valueArrayFloat.size(); i++) {
            pieDataArray.add(new PieEntry(valueArrayFloat.get(i), valueArrayString.get(i)));
        }

        rowLayout.addView(rowView);

    }

}