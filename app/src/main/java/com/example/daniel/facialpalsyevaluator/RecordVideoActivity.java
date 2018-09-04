package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import daniel.example.com.facialpalsyevaluator.R;

public class RecordVideoActivity extends AppCompatActivity {

    private static final int VIDEO_CAPTURE = 101;
    private Uri fileUri;

    List<Patient> pList;
    int pTag;
    int aptTag;
    String prevPage;

//    public void startRecording(View view)
//    {
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_video);
        Intent i = getIntent();

        pList = (List<Patient>) i.getSerializableExtra("pList");
        pTag = (int)i.getSerializableExtra("pTag");
        aptTag = (int)i.getSerializableExtra("aptTag");
        prevPage = (String) i.getSerializableExtra("prevPage");

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, VIDEO_CAPTURE);

        Button recordButton =
                (Button) findViewById(R.id.recordButton);

        if (!hasCamera())
            recordButton.setEnabled(false);

    }

    private boolean hasCamera() {
        return (getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_ANY));
    }

    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {

        Uri videoUri = data.getData();

        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video saved to:\n" +
                        videoUri, Toast.LENGTH_LONG).show();
                pList.get(pTag).appointments.get(aptTag).videos.add(videoUri.toString());

                //startActivity(getIntent());
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
        //   i.putExtra("rowNum", );
        startActivity(i);
    }
}

