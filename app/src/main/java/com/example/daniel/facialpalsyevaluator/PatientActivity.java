package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import daniel.example.com.facialpalsyevaluator.R;


public class PatientActivity extends AppCompatActivity {

    List<Patient> pList;
    int tag;
    TableLayout appointmentsTable;
    String prevPage;
    ScrollView scrollView;
    HorizontalScrollView horizontalScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        Intent i = getIntent();
        pList = (List<Patient>) i.getSerializableExtra("pList");
        tag = (int)i.getSerializableExtra("tag");

        prevPage = (String) i.getSerializableExtra("prevPage");

        TableLayout detailsTable = findViewById(R.id.detailsTable);
        appointmentsTable = findViewById(R.id.appointmentsTable);

        scrollView = findViewById(R.id.layoutApt);
        horizontalScrollView= findViewById(R.id.horizontalViewApt);
        final TextView textViewToChange = findViewById(R.id.textView);
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
        for (String x : data) {
            textColBuilder(x, tr);
        }
        textColBuilder("   ", tr);
        table.addView(tr);
    }

    private void updateAppointmentsTable(TableLayout table, List<Appointment> apt) {

        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

        for (Appointment x : apt) {
            aptColBuilder(x, tr);
        }
        table.addView(tr);
    }

    private void textColBuilder(String data, TableRow tr) {

        TextView tv = new TextView(this);
        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv.setText(data);
        tv.setTextSize(17);
        tv.setPadding(30, 50, 0, 0);
        tr.addView(tv);
    }

    private void aptColBuilder(Appointment apt, TableRow tr) {

        final ImageButton folder = new ImageButton(this);
        folder.setImageResource(R.drawable.folder_icon);
        folder.setTag(apt.apNum);
        tr.addView(folder);

        folder.setClickable(true);
        folder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int rowNum = (Integer) folder.getTag();
                Intent i = new Intent(PatientActivity.this, AppointmentActivity.class);

                i.putExtra("pList", (Serializable) pList);
                i.putExtra("pTag", tag);
                i.putExtra("aptTag", rowNum-1);
                i.putExtra("prevPage", prevPage);

                startActivity(i);
            }
        });
    }

    public static List<String> GenerateAptHeaders(List<Appointment> apt) {

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

        startActivity(i);
    }

    public void back (View view) {

        Intent i = new Intent();
        switch (prevPage) {

            case "search":
            i = new Intent(PatientActivity.this, PatResultsActivity.class);
            break;

            case "home":
            i = new Intent(PatientActivity.this, HomePageActivity.class);
            break;

            default:
                break;
        }
        i.putExtra("pList", (Serializable) pList);
        startActivity(i);
    }
}
