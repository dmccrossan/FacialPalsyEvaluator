package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import daniel.example.com.facialpalsyevaluator.R;

public class NotesActivity extends AppCompatActivity {


    EditText text;
    List<Patient> pList;
    int pTag;
    int aptTag;
    String prevPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();

        pList = (List<Patient>) i.getSerializableExtra("pList");
        pTag = (int) i.getSerializableExtra("pTag");
        aptTag = (int) i.getSerializableExtra("aptTag");
        prevPage = (String) i.getSerializableExtra("prevPage");
        setContentView(R.layout.activity_notes);

        text = findViewById(R.id.note);
        text.setText( pList.get(pTag).appointments.get(aptTag).notes);
    }

    public void done(View view) {


        text = findViewById(R.id.note);
        pList.get(pTag).appointments.get(aptTag).notes = text.getText().toString();

        Intent i = new Intent(NotesActivity.this, AppointmentActivity.class);

        i.putExtra("pList", (Serializable) pList);
        i.putExtra("pTag", pTag);
        i.putExtra("aptTag", aptTag);
        i.putExtra("prevPage", prevPage);

        startActivity(i);

    }
}
