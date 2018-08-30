package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import daniel.example.com.facialpalsyevaluator.R;

public class AppointmentActivity extends AppCompatActivity {

    Appointment apt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        Intent i = getIntent();
        apt = (Appointment) i.getSerializableExtra("apt");

        TextView title = (TextView) findViewById(R.id.textView2);
        title.setText("Appointment Details - "+ apt.apDate);
    }

    public void done(View view) {
        finish();
    }




}
