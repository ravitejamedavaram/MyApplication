package com.example.ravitejam.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import db.DbModel;

public class SummaryActivity extends AppCompatActivity {

    TextView finalSummary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        finalSummary = findViewById(R.id.finalPinsList);
        finalSummary.setText("List of pins");
        DbModel dbModel = new DbModel(SummaryActivity.this);
        Cursor cursor = dbModel.getPins();
        cursor.moveToFirst();

        String pinName, pinNumber, currentText;
if (cursor.moveToFirst()) {
    do {
        pinName = cursor.getString(cursor.getColumnIndex(DbModel.pinName));
        pinNumber = cursor.getString(cursor.getColumnIndex(DbModel.pinId));
        currentText = finalSummary.getText().toString();
        finalSummary.setText(currentText + " ; " + pinName + " : " + pinNumber);
    } while (cursor.moveToNext());

}
    }
}
