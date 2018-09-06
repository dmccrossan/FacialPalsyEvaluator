package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.io.Serializable;
import java.util.List;
import daniel.example.com.facialpalsyevaluator.R;

public class PatResultsActivity extends AppCompatActivity {

    List<Patient> pList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final List<Patient> patientList;

        Intent i = getIntent();
        patientList = (List<Patient>) i.getSerializableExtra("pList");
        setContentView(R.layout.activity_pat_results);

        TableLayout table = findViewById(R.id.table);

        update("ID", "DOB", table, "HEADER", 0, patientList);
        int counter = 0;
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
        tv.setPaintFlags(tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv.setPadding(70, 50, 50, 0);
        tr.addView(tv);

        if (flag.equals("HEADER")) {
        } else {
            tr.setClickable(true);
            tr.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    int rowNum = (Integer) tr.getTag();
                    Intent i = new Intent(PatResultsActivity.this, PatientActivity.class);

                    i.putExtra("pList", (Serializable) patientList);
                    i.putExtra("tag", rowNum - 1);
                    i.putExtra("prevPage", "search");
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
        table.addView(tr);
    }

    public void back(View view) {

        Intent i = new Intent(PatResultsActivity.this, HomePageActivity.class);

        i.putExtra("pList", (Serializable) pList);
        startActivity(i);
    }
}


