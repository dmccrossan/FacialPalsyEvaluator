package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import daniel.example.com.facialpalsyevaluator.LoginActivity;
import daniel.example.com.facialpalsyevaluator.R;


public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        Patient p = new Patient();
        Log.d("myTag", p.CHI);
    }

    public void search (View view){

            startActivity(new Intent(HomePageActivity.this, PatResultsActivity.class));
        }
}
