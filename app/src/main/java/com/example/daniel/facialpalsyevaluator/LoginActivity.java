package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import daniel.example.com.facialpalsyevaluator.R;

/*
Creates log in page on app
 */

public class LoginActivity extends AppCompatActivity {

    static EditText username;
    static EditText password;

    // Builds the page
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    // Loads home page if user is authenticated
    public void login(View view) {

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        boolean authUser = authenticate(username.getText().toString(), password.getText().toString(), username, password);

        if (authUser) {
            startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
        }
    }

    // Authenticates user
    public static boolean authenticate(String u, String p, EditText username, EditText password) {

        if (u.contains("@") && p.length() >= 6) {
            return true;
        } else {
            username.setText("");
            password.setText("");
            return false;
        }

    }
}
