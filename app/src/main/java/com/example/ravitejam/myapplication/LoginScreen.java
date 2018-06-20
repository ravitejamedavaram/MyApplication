package com.example.ravitejam.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import db.DbModel;

public class LoginScreen extends AppCompatActivity {

    EditText username, password;
    Button loginBtn;
    TextView message;
    DbModel dbModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        message = findViewById(R.id.messageText);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("password")) {
                    dbModel=new DbModel(LoginScreen.this);
                    Cursor hostAndPort = dbModel.getHostAndPort();
                    Cursor pinName = dbModel.getPins();
                    hostAndPort.moveToFirst();
                    try{
                        String hp = hostAndPort.getString(hostAndPort.getColumnIndex(DbModel.hostColumn));
//                        Intent intent = new Intent(LoginScreen.this, SummaryActivity.class);
//                        startActivity(intent);
                    }catch (CursorIndexOutOfBoundsException cioe){
//                        Intent intent = new Intent(LoginScreen.this, MainActivity.class);
//                        startActivity(intent);
                    }
                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    message.setText("Invalid Credentials");
                }
            }
        });
    }
}
