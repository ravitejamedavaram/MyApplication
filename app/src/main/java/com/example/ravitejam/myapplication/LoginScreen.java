package com.example.ravitejam.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {

    EditText username, password;
    Button loginBtn;
    TextView message;

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
                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    message.setText("Invalid Credentials");
                }
            }
        });
    }
}
