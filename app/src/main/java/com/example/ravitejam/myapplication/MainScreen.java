package com.example.ravitejam.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import db.DbModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class MainScreen extends AppCompatActivity {

    TextView responseTextView;
    DbModel db;
    Service service;
    EditText pinNumber;
    Button testBtn, addBtn, doneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        testBtn = findViewById(R.id.TestBtn);
        pinNumber = findViewById(R.id.pinNumberEdit);
        addBtn = findViewById(R.id.addBtn);
        doneBtn = findViewById(R.id.doneBtn);
        db = new DbModel(MainScreen.this);
        Cursor cursor = db.getHostAndPort();
        cursor.moveToFirst();
        responseTextView = (TextView) findViewById(R.id.response);
        String host = cursor.getString(cursor.getColumnIndex(DbModel.hostColumn));
        String port = cursor.getString(cursor.getColumnIndex(DbModel.portColumn));
        responseTextView.setText("Connected to Host " + host +
                ":" + port);

        final String url = "http://" + host +":"+ port;

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiUtils.BASE_URL = url;
                service = ApiUtils.getAPIService();
                if (pinNumber==null || pinNumber.getText().toString().trim().equalsIgnoreCase("")){
                    responseTextView.setText("Enter a pin number");
                }else {
                    responseTextView.setText("");
                    String status = "ON";
                    sendSignal(pinNumber.getText().toString(), status);
                }

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this,AddPins.class);
                intent.putExtra("pinNumber",pinNumber.getText().toString());
                startActivity(intent);
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this,SummaryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void sendSignal(String portNumber, String status) {
        Message message = new Message();
        message.setPinNumber(portNumber);
        message.setStatus(status);
        service.sendToPi(message).enqueue(new Callback<Message>() {
//        service.test().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                responseTextView.setText("Message Signal");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                responseTextView.setText("failed to send signal");
                System.out.println(t.getMessage());
            }
        });

    }
}


