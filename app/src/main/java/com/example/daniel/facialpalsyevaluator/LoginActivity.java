package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import daniel.example.com.facialpalsyevaluator.R;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        boolean authUser = authenticate(username.getText().toString(), password.getText().toString());

        if (authUser) {
            startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
        }
    }

    private boolean authenticate(String u, String p) {

//        if (u.contains("@") && p.length()>=6) {
//            return true;
//        } else {
//            username.setText("");
//            password.setText("");
//            return false;
//        }
        return true;
    }
}
