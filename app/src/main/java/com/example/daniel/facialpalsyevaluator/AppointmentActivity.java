package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import daniel.example.com.facialpalsyevaluator.R;

/*
Creates page on app that will display appointments
 */

public class AppointmentActivity extends AppCompatActivity {


    int pTag;
    int aptTag;
    String prevPage;
    List<Patient> pList;
    List<String> graphList;
    TableLayout vidTable;
    TableLayout graphTable;

    // loads and sets variables and sets up tables to be displayed for the page
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        Intent i = getIntent();

        pList = (List<Patient>) i.getSerializableExtra("pList");
        pTag = (int) i.getSerializableExtra("pTag");
        aptTag = (int) i.getSerializableExtra("aptTag");
        prevPage = (String) i.getSerializableExtra("prevPage");


        TextView title = findViewById(R.id.textView2);
        title.setText("Appointment Details - " + pList.get(pTag).appointments.get(aptTag).apDate);
        vidTable = findViewById(R.id.vidTable);
        graphTable = findViewById(R.id.graphTable);


        updateTable(vidTable, pList.get(pTag).appointments.get(aptTag).videos, "vid");
        graphList = new ArrayList<>();
        graphList.add(pList.get(pTag).appointments.get(aptTag).facograms);
        if (pList.get(pTag).appointments.get(aptTag).videos.size() > 0) {
            updateTable(graphTable, graphList, "graph");
        }
    }

    //  Creates the row(s) for the tables on screen
    public void updateTable(TableLayout table, List<String> data, String flag) {

        TableRow tr = new TableRow(this);

        switch (flag) {

            case "vid":
                int counter = 0;
                for (String x : data) {
                    vidColBuilder(tr, counter);
                    counter++;
                }
                break;

            case "graph":
                graphColBuilder(tr, 0);
                break;

            default:
                break;
        }
        table.addView(tr);
    }

    // Creates column(s) for the table and populates them
    private void graphColBuilder(TableRow tr, final int counter) {

        final ImageButton graph = new ImageButton(this);
        graph.setImageResource(R.drawable.facogram_icon);
        graph.setScaleX(0.90f);
        graph.setScaleY(0.90f);
        graph.setTag(counter);
        tr.addView(graph);
    }

    // Creates column(s) for the table and populates them
    private void vidColBuilder(TableRow tr, final int counter) {

        final ImageButton vid = new ImageButton(this);
        vid.setImageResource(R.drawable.video_icon);
        vid.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        vid.setTag(counter);
        tr.addView(vid);

        vid.setClickable(true);
        vid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(AppointmentActivity.this, VideoPlayerActivity.class);

                i.putExtra("pList", (Serializable) pList);
                i.putExtra("pTag", pTag);
                i.putExtra("aptTag", aptTag);
                i.putExtra("vidTag", counter);
                i.putExtra("prevPage", prevPage);

                startActivity(i);
            }
        });
    }

    //    calls the finish method
    public void done(View view) {

        finish();
    }

    // goes back to the previous page
    public void finish() {

        Intent i = new Intent(AppointmentActivity.this, PatientActivity.class);
        i.putExtra("pList", (Serializable) pList);
        i.putExtra("tag", pTag);
        i.putExtra("prevPage", prevPage);
        startActivity(i);
    }

    //    Loads notes page
    public void notes(View view) {

        Intent i = new Intent(AppointmentActivity.this, NotesActivity.class);

        i.putExtra("pList", (Serializable) pList);
        i.putExtra("pTag", pTag);
        i.putExtra("aptTag", aptTag);
        i.putExtra("prevPage", prevPage);

        startActivity(i);
    }

    // Loads video recording page
    public void recordVideo(View view) {

        Intent i = new Intent(AppointmentActivity.this, RecordVideoActivity.class);

        i.putExtra("pList", (Serializable) pList);
        i.putExtra("pTag", pTag);
        i.putExtra("aptTag", aptTag);
        i.putExtra("prevPage", prevPage);

        startActivity(i);
    }
}
