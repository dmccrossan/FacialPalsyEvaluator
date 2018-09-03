package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.List;

import daniel.example.com.facialpalsyevaluator.R;

public class AppointmentActivity extends AppCompatActivity {

    private StorageReference mStorageRef;

    Appointment apt;
    List<Patient> pList;
    int pTag;
    int aptTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        Intent i = getIntent();

        pList = (List<Patient>) i.getSerializableExtra("pList");
        pTag = (int)i.getSerializableExtra("pTag");
        aptTag = (int)i.getSerializableExtra("aptTag");

       // apt = (Appointment) i.getSerializableExtra("apt");

        TextView title = (TextView) findViewById(R.id.textView2);
        title.setText("Appointment Details - " + pList.get(pTag).appointments.get(aptTag).apDate);
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public void done(View view){finish();}

    public void finish () {

//        Intent intent = new Intent();
//        pList.get(pTag).appointments.get(aptTag).apDate = "esrdtfyguhj";
//        intent.putExtra("pList", (Serializable) pList);
//        setResult(RESULT_OK, intent);
//        super.finish();



        Intent i = new Intent(AppointmentActivity.this, PatientActivity.class);

        i.putExtra("pList" ,(Serializable) pList);
        i.putExtra("tag" ,pTag);
        startActivity(i);
    }


    public void playVideo(View view) {

        Intent i = new Intent(AppointmentActivity.this, VideoPlayerActivity.class);

        i.putExtra("pList", (Serializable) pList);
        i.putExtra("pTag", pTag);
        i.putExtra("aptTag", aptTag);
        i.putExtra("vidTag", 0);


        startActivity(i);
    }


    public void recordVideo(View view) {

        Intent i = new Intent(AppointmentActivity.this, RecordVideoActivity.class);

        i.putExtra("pList", (Serializable) pList);
        i.putExtra("pTag", pTag);
        i.putExtra("aptTag", aptTag);

        startActivity(i);

     //   startActivity(getIntent());
    }
}
