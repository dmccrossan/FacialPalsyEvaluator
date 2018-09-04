package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
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
    String prevPage;
    TableLayout vidTable;
    TableLayout graphTable;
    TableLayout notesTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        Intent i = getIntent();

        pList = (List<Patient>) i.getSerializableExtra("pList");
        pTag = (int)i.getSerializableExtra("pTag");
        aptTag = (int)i.getSerializableExtra("aptTag");
        prevPage = (String) i.getSerializableExtra("prevPage");

       // apt = (Appointment) i.getSerializableExtra("apt");

        TextView title = (TextView) findViewById(R.id.textView2);
        title.setText("Appointment Details - " + pList.get(pTag).appointments.get(aptTag).apDate);
        vidTable = (TableLayout) findViewById(R.id.vidTable);
        graphTable = (TableLayout) findViewById(R.id.graphTable);
        notesTable = (TableLayout) findViewById(R.id.notesTable);

        updateVidTable(vidTable, pList.get(pTag).appointments.get(aptTag).videos);
    }

public void updateVidTable(TableLayout vidTable,List<String> videos){
    TableRow tr = new TableRow(this);

    tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

    int counter = 0;
    for (String x : videos) {
        vidColBuilder(x, tr, videos,counter);
        counter++;
    }

    //  aptColBuilder("   ", tr);

    vidTable.addView(tr);

}

    private void vidColBuilder(String path, TableRow tr, final List<String> vidList,final int counter) {

        final ImageButton vid = new ImageButton(this);
        vid.setImageResource(R.drawable.video_icon);
        vid.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        vid.setScaleX(0.25f); // <- resized by scaling
        vid.setScaleY(0.25f);

        vid.setTag(counter);
        tr.addView(vid);


        vid.setClickable(true);
        vid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //needs to be changed to going to a new page
                // startActivity(new Intent(PatResultsActivity.this, PatientActivity.class));
                int rowNum = (Integer) vid.getTag();
                Intent i = new Intent(AppointmentActivity.this, VideoPlayerActivity.class);

                i.putExtra("pList", (Serializable) pList);
                i.putExtra("pTag", pTag);
                i.putExtra("aptTag", aptTag);
                i.putExtra("vidTag", counter);
                i.putExtra("prevPage" ,prevPage);


                startActivity(i);
            }
        });
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
        i.putExtra("prevPage" ,prevPage);
        startActivity(i);
    }


    public void playVideo(View view) {

        Intent i = new Intent(AppointmentActivity.this, VideoPlayerActivity.class);

        i.putExtra("pList", (Serializable) pList);
        i.putExtra("pTag", pTag);
        i.putExtra("aptTag", aptTag);
        i.putExtra("vidTag", 0);
        i.putExtra("prevPage" ,prevPage);


        startActivity(i);
    }


    public void recordVideo(View view) {

        Intent i = new Intent(AppointmentActivity.this, RecordVideoActivity.class);

        i.putExtra("pList", (Serializable) pList);
        i.putExtra("pTag", pTag);
        i.putExtra("aptTag", aptTag);
        i.putExtra("prevPage" ,prevPage);

        startActivity(i);

     //   startActivity(getIntent());
    }
}
