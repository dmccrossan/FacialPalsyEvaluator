package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import java.util.Arrays;
import java.util.List;

import daniel.example.com.facialpalsyevaluator.R;

import static java.security.AccessController.getContext;

public class PatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        Intent i = getIntent();
        Patient p = (Patient) i.getSerializableExtra("key");
        TableLayout detailsTable = (TableLayout)findViewById(R.id.detailsTable);
     //  RelativeLayout rl=(RelativeLayout)findViewById(R.id.detailsTable);
     //   ScrollView sv = new ScrollView(this).findViewById(R.id.scrollView1);
    //    sv.addView(detailsTable);
     //   rl.addView(sv);

        final TextView textViewToChange = (TextView) findViewById(R.id.textView);
        String test = "Patient Details - ".concat(p.chi);
        textViewToChange.setText(test);

                List<String> headers= Arrays.asList("Patient", "Name","DOB","Address");

        update (detailsTable, headers);
        update( detailsTable, p.toList());
    }

    private void update (TableLayout table, List<String> data) {

        TableRow tr = new TableRow(this);

/*
*******************************************************************************
 ********************************************************************************
* FIX HORIZONTAL SCROLLING
* *******************************************************************************
********************************************************************************
 */
        tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        for ( String x : data){
            colBuilder(x, tr);
        }


        colBuilder("   ", tr);


        table.addView(tr);
    }

private void colBuilder (String data, TableRow tr) {

    TextView tv = new TextView(this);
    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
    tv.setText(data);
    tv.setTextSize(17);
    tv.setPadding(30, 50, 0, 0);
    tr.addView(tv);
}
}
