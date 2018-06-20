package com.example.ravitejam.myapplication;

import android.database.Cursor;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import db.DbModel;

public class SummaryActivity extends AppCompatActivity {

    TextView finalSummary;
    ConstraintLayout constraintLayout, sampleLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        constraintLayout = (ConstraintLayout) findViewById(R.id.summary);
        finalSummary = findViewById(R.id.finalPinsList);
        finalSummary.setText("List of pins");
        DbModel dbModel = new DbModel(SummaryActivity.this);
        Cursor cursor = dbModel.getPins();
        cursor.moveToFirst();
        int i = 0;
        GridView gv = (GridView) findViewById(R.id.gv);
        List<String> btnsList = new ArrayList<String>();
//        final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>
//                (this,android.R.layout.simple_list_item_1, btnsList);


        String pinName, pinNumber, currentText;
        if (cursor.moveToFirst()) {
            do {
                pinName = cursor.getString(cursor.getColumnIndex(DbModel.pinName));
                pinNumber = cursor.getString(cursor.getColumnIndex(DbModel.pinId));
                currentText = finalSummary.getText().toString();
                finalSummary.setText(currentText + " ; " + pinName + " : " + pinNumber);

                btnsList.add(pinName);
                i++;
            } while (cursor.moveToNext());

        }
        gv.setAdapter(new ButtonAdapter(this,btnsList));

    }
}
