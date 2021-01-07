package com.example.lpd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class BarSheet extends AppCompatActivity {

    LinearLayout rowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spread_sheet);

        Intent intent = getIntent();

        ArrayList<Float> valueArrayFloat = (ArrayList<Float>)intent.getSerializableExtra("arrayFloat");
        ArrayList<Integer> valueArrayInteger = intent.getIntegerArrayListExtra("arrayInteger");

        rowLayout = findViewById(R.id.rowLayout);
        for (int i = 0; i < 100; i++) {
            addView(i, valueArrayFloat, valueArrayInteger);
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
        ArrayList<Integer> valueArrayListInteger = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            EditText textName = findViewById(1010000 + i);
            EditText textValue = findViewById(1020000 + i);
            String value = textValue.getText().toString();
            String pos = textName.getText().toString();
            if (value.equals("") || pos.equals("")) {
                continue;
            }
            valueArrayListInteger.add(Integer.parseInt(pos));
            valueArrayListFloat.add(Float.parseFloat(value));
        }
        Intent resultIntent = new Intent();
        resultIntent.putExtra("arrayFloat", valueArrayListFloat);
        resultIntent.putIntegerArrayListExtra("arrayInteger", valueArrayListInteger);
        setResult(RESULT_OK, resultIntent);
        finish();
    }


    @SuppressLint("SetTextI18n")
    private void addView(int num, ArrayList<Float> valueArrayFloat, ArrayList<Integer> valueArrayInteger) {
        final View rowView = getLayoutInflater().inflate(R.layout.sheet_line, null, false);
        TextView textNum = rowView.findViewById(R.id.textNum);
        EditText textPos = rowView.findViewById(R.id.textName);
        EditText textValue = rowView.findViewById(R.id.textValue);

        textNum.setText((num+1)+"");
        textPos.setId(1010000 + num);
        textPos.setHint("Position");
        textPos.setInputType(2);
        textValue.setId(1020000 + num);
        if (num < valueArrayFloat.size()) {
            textPos.setText(valueArrayInteger.get(num).toString());
            textValue.setText(valueArrayFloat.get(num).toString());
        }

        rowLayout.addView(rowView);

    }

}