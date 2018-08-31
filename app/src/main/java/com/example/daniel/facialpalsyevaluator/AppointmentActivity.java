package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import daniel.example.com.facialpalsyevaluator.R;

public class AppointmentActivity extends AppCompatActivity {

    private StorageReference mStorageRef;

    Appointment apt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        Intent i = getIntent();
        apt = (Appointment) i.getSerializableExtra("apt");

        TextView title = (TextView) findViewById(R.id.textView2);
        title.setText("Appointment Details - "+ apt.apDate);
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public void done(View view) {
        finish();
    }


public void playVideo(View view){

    Intent i = new Intent(AppointmentActivity.this, VideoPlayerActivity.class);

    startActivity(i);
}

}
