package com.example.daniel.facialpalsyevaluator;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import daniel.example.com.facialpalsyevaluator.R;

public class PatResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final List<Patient> patientList;// = new ArrayList<>();

        Intent i = getIntent();
        patientList = (List<Patient>) i.getSerializableExtra("key");
        setContentView(R.layout.activity_pat_results);


        TableLayout table = (TableLayout) findViewById(R.id.table);


//        will call update for however big the search results list is
//        will be passed in as a parameter from previous page
        update("ID", "DOB", table, "HEADER", 0, patientList);
        int counter = 0; // this will be used to provide the row number in the table and the patient selected from the table

// for each loop used as at a quick glace you can see the type of object in the list
        for (Patient p : patientList) {
            counter++;
            update(p.chi, p.dob, table, "data", counter, patientList);
        }


    }

    private void update(String x, String y, TableLayout table, String flag, final int position, final List<Patient> patientList) {


        final TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        tr.setTag(position);

        TextView tv = new TextView(this);
        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv.setText(x);
        tv.setTextSize(20);
        tv.setPadding(70, 50, 50, 0);
        tr.addView(tv);

        if (flag.equals("HEADER")) {
        } else {
            tr.setClickable(true);
            tr.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //needs to be changed to going to a new page
                    // startActivity(new Intent(PatResultsActivity.this, PatientActivity.class));
                    int rowNum = (Integer) tr.getTag();
                    Intent i = new Intent(PatResultsActivity.this, PatientActivity.class);

                    i.putExtra("pList" ,(Serializable) patientList);
                    i.putExtra("tag" ,rowNum-1);
                    startActivity(i);
                }
            });
        }


        TextView tv1 = new TextView(this);
        tv1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv1.setText(y);
        tv1.setTextSize(20);
        tv1.setPadding(70, 50, 50, 0);
        tr.addView(tv1);
//        tr.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //needs to be changed to going to a new page
//                startActivity(new Intent(PatResultsActivity.this, PatientActivity.class));
//            }});

        table.addView(tr);

    }

    public void back(View view) {

        finish();
    }

//    public boolean onOptionsItemSelected(MenuItem item){
//        Intent myIntent = new Intent(getApplicationContext(), HomePageActivity.class);
//        startActivityForResult(myIntent, 0);
//        return true;
//
//    }
}
