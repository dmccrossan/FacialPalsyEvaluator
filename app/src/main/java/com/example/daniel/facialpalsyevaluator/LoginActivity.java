package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import daniel.example.com.facialpalsyevaluator.R;


import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {

        boolean authUser = authenticate("daniel", "pass");

        if (authUser) {
//        if valid user, go to homepage

            Log.d("myTag", "This is my message");
//                    setContentView(R.layout.activity_home_page);
            startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
        }
    }

    private boolean authenticate(String username, String password) {


        return true;
    }

    List<Patient> pList;


}
