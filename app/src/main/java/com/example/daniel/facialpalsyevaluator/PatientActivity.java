package com.example.daniel.facialpalsyevaluator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import daniel.example.com.facialpalsyevaluator.R;

public class PatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        TableLayout detailsTable = (TableLayout)findViewById(R.id.detailsTable);

        update( detailsTable, new Patient());
    }

    private void update (TableLayout table, Patient p ) {

        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));


        TextView tv = new TextView(this);
        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv.setText(p.fname);
        tv.setTextSize(50);
        tr.setPadding(50,50,0,0);
        tr.addView(tv);

        table.addView(tr);
    }
}
