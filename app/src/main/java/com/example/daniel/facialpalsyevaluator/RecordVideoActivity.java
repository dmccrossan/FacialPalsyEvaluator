package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import daniel.example.com.facialpalsyevaluator.R;

/*
Opens the camera and allows user to record a video
 */
public class RecordVideoActivity extends AppCompatActivity {

    private static final int VIDEO_CAPTURE = 101;
    List<Patient> pList;
    int pTag;
    int aptTag;
    String prevPage;

    // loads and sets variables and control buttons for the page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_video);
        Intent i = getIntent();

        pList = (List<Patient>) i.getSerializableExtra("pList");
        pTag = (int) i.getSerializableExtra("pTag");
        aptTag = (int) i.getSerializableExtra("aptTag");
        prevPage = (String) i.getSerializableExtra("prevPage");

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, VIDEO_CAPTURE);

        Button recordButton = findViewById(R.id.recordButton);

        if (!hasCamera())
            recordButton.setEnabled(false);
    }

    // Checks if user phone has a camera
    private boolean hasCamera() {

        return (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY));
    }

    // Saves the video and then returns to previous page
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Uri videoUri = data.getData();

        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video saved to:\n" +
                        videoUri, Toast.LENGTH_LONG).show();
                pList.get(pTag).appointments.get(aptTag).videos.add(videoUri.toString());

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video",
                        Toast.LENGTH_LONG).show();
            }
        }
        Intent i = new Intent(RecordVideoActivity.this, AppointmentActivity.class);

        i.putExtra("pList", (Serializable) pList);
        i.putExtra("pTag", pTag);
        i.putExtra("aptTag", aptTag);
        i.putExtra("prevPage", prevPage);
        startActivity(i);
    }
}

