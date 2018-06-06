package com.example.ravitejam.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import db.DbModel;

public class AddPins extends AppCompatActivity {

    EditText finalLabel;
    Button finalAddBtn;
    DbModel dbModel;
    Button backToTest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pins);

        finalLabel = findViewById(R.id.finalLabel);
        finalAddBtn = findViewById(R.id.finalAddBtn);
        backToTest = findViewById(R.id.backToTest);
        final String pinNumber = getIntent().getExtras().get("pinNumber").toString();
        finalLabel.setText("GPIO-"+pinNumber);

        finalAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbModel = new DbModel(AddPins.this);
                String finalLabel = ((EditText)findViewById(R.id.finalLabel)).getText().toString();
                dbModel.insertPinData(finalLabel, pinNumber);
            }
        });

        backToTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPins.this,MainScreen.class);
                startActivity(intent);
            }
        });

    }
}
