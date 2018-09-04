package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import daniel.example.com.facialpalsyevaluator.R;

import static java.security.AccessController.getContext;

public class PatientActivity extends AppCompatActivity {

    List<Patient> pList;
    int tag;
    TableLayout appointmentsTable;
    private StorageReference mStorageRef;
    String prevPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        Intent i = getIntent();
        pList = (List<Patient>) i.getSerializableExtra("pList");
        tag = (int)i.getSerializableExtra("tag");

        prevPage = (String) i.getSerializableExtra("prevPage");

        TableLayout detailsTable = (TableLayout) findViewById(R.id.detailsTable);
        appointmentsTable = (TableLayout) findViewById(R.id.appointmentsTable);


        final TextView textViewToChange = (TextView) findViewById(R.id.textView);
        String test = "Patient Details - ".concat(pList.get(tag).chi);
        textViewToChange.setText(test);

        List<String> detailsHeaders = Arrays.asList("Patient", "Name", "DOB", "Address");


        updateTextTable(detailsTable, detailsHeaders);
        updateTextTable(detailsTable, pList.get(tag).toList());


        updateTextTable(appointmentsTable, GenerateAptHeaders(pList.get(tag).appointments));
        updateAppointmentsTable(appointmentsTable, pList.get(tag).appointments);
    }


    private void updateTextTable(TableLayout table, List<String> data) {

        TableRow tr = new TableRow(this);

        /*
         *******************************************************************************
         ********************************************************************************
         * FIX HORIZONTAL SCROLLING
         * *******************************************************************************
         ********************************************************************************
         */
        tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        for (String x : data) {
            textColBuilder(x, tr);
        }

        textColBuilder("   ", tr);

        table.addView(tr);
    }

    private void updateAppointmentsTable(TableLayout table, List<Appointment> apt) {

        TableRow tr = new TableRow(this);

        /*
         *******************************************************************************
         ********************************************************************************
         * FIX HORIZONTAL SCROLLING
         * *******************************************************************************
         ********************************************************************************
         */
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        for (Appointment x : apt) {
            aptColBuilder(x, tr, apt);
        }

        //  aptColBuilder("   ", tr);

        table.addView(tr);
    }

    private void textColBuilder(String data, TableRow tr) {

        TextView tv = new TextView(this);
        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv.setText(data);
        tv.setTextSize(17);
        tv.setPadding(30, 50, 0, 0);
        tr.addView(tv);
    }

    private void aptColBuilder(Appointment apt, TableRow tr, final List<Appointment> aptList) {


        // TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        //lp.addRule(TableRow.CENTER_IN_PARENT,1);
        //lp.height = 100;
        //lp.width = 100;


        final ImageButton folder = new ImageButton(this);
        folder.setImageResource(R.drawable.folder_icon);
        folder.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        // folder.setLayoutParams(lp);
        folder.setScaleX(0.25f); // <- resized by scaling
        folder.setScaleY(0.25f);
        // folder.setScaleType(ImageView.ScaleType.FIT_XY);
        folder.setTag(apt.apNum);
        //  tr.setGravity(Gravity.START);

        tr.addView(folder);
        //tr.setPadding(-100,-50,-500,-50);

        folder.setClickable(true);
        folder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //needs to be changed to going to a new page
                // startActivity(new Intent(PatResultsActivity.this, PatientActivity.class));
                int rowNum = (Integer) folder.getTag();
                Intent i = new Intent(PatientActivity.this, AppointmentActivity.class);

                i.putExtra("pList", (Serializable) pList);
                i.putExtra("pTag", tag);
                i.putExtra("aptTag", rowNum-1);
                i.putExtra("prevPage", prevPage);
                //   i.putExtra("rowNum", );
                startActivity(i);
            }
        });
    }

    private List<String> GenerateAptHeaders(List<Appointment> apt) {

        List<String> data = new ArrayList<>();

        for (Appointment a : apt) {
            data.add(a.apNum + ". " + a.apDate);
        }
        return data;
    }

    public void createAppointment(View view) {

        Appointment a = new Appointment();
        a.apNum = pList.get(tag).appointments.size() + 1;
        pList.get(tag).appointments.add(a);

        finish();
        startActivity(getIntent());

        Intent i = new Intent(PatientActivity.this, AppointmentActivity.class);

        i.putExtra("pList", (Serializable) pList);
        i.putExtra("pTag", tag);
        i.putExtra("aptTag", a.apNum-1);
        i.putExtra("prevPage", prevPage);

        //  i.putExtra("rowNum", a.apNum);
        startActivity(i);
    }



    public void back (View view) {

        Intent i;
        if (prevPage.equals("search")) {
            i = new Intent(PatientActivity.this, PatResultsActivity.class);


        }
        else if (prevPage.equals("home")){
            i = new Intent(PatientActivity.this, HomePageActivity.class);
        }
        else{
            i = new Intent(PatientActivity.this, HomePageActivity.class);
            // should never hit this line
        }
        i.putExtra("pList", (Serializable) pList);
        startActivity(i);

    }

}
