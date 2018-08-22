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

import daniel.example.com.facialpalsyevaluator.R;

public class PatResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat_results);
        TableLayout table = (TableLayout)findViewById(R.id.table);



//        will call update for however big the search results list is
//        will be passed in as a parameter from previous page
        update("blue", table, "HEADER");
        update("green", table, "gh");
        update("red", table, "dhj");


    }

    private void update(String text, TableLayout table, String flag) {


        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));


        TextView tv = new TextView(this);
        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv.setText(text);
        tv.setTextSize(50);
        tr.setPadding(50,50,0,0);
        tr.addView(tv);

        if (flag.equals("HEADER")) {
        }
        else {
            tr.setClickable(true);
            tr.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //needs to be changed to going to a new page
                    startActivity(new Intent(PatResultsActivity.this, PatientActivity.class));
                }
            });
        }


        TextView tv1 = new TextView(this);
        tv1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv1.setText(text);
        tv1.setTextSize(50);
        tr.setPadding(50,50,0,0);
        tr.addView(tv1);
//        tr.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //needs to be changed to going to a new page
//                startActivity(new Intent(PatResultsActivity.this, PatientActivity.class));
//            }});

        table.addView(tr);

    }

    public void back (View view) {

        finish();
    }

//    public boolean onOptionsItemSelected(MenuItem item){
//        Intent myIntent = new Intent(getApplicationContext(), HomePageActivity.class);
//        startActivityForResult(myIntent, 0);
//        return true;
//
//    }
}
