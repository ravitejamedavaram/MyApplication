package com.example.ravitejam.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import db.DbModel;

public class MainActivity extends AppCompatActivity {

    public static EditText host, port;
    public static Button connect;
    DbModel db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        host = (EditText) findViewById(R.id.host);
        port = (EditText) findViewById(R.id.port);
        connect = (Button) findViewById(R.id.connect);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String hostName = host.getText().toString();
                final String portNumber = port.getText().toString();
                db = new DbModel(MainActivity.this);
                db.insertData(hostName, portNumber);
                Intent intent = new Intent(MainActivity.this, MainScreen.class);
                startActivity(intent);
            }
        });
    }
}
