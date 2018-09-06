package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import daniel.example.com.facialpalsyevaluator.R;

/*
Creates home page that allows user to search for existing patients or create new patients
 */
public class HomePageActivity extends AppCompatActivity {

    static List<Patient> patientList = new ArrayList();

    EditText editTextSearch;
    EditText editTextFName;
    EditText editTextLName;
    EditText editTextDob;
    EditText editTextAddress;

    // loads and sets variables and sets up tables to be displayed for the page
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        patientList = (List<Patient>) i.getSerializableExtra("pList");

        if (patientList == null) {
            patientList = new ArrayList();
            loadPatients();
            loadAppointments(patientList);
        }

        setContentView(R.layout.activity_home_page);

        editTextSearch = findViewById(R.id.password);
        editTextFName = findViewById(R.id.addFName);
        editTextLName = findViewById(R.id.addLName);
        editTextDob = findViewById(R.id.addDob);
        editTextAddress = findViewById(R.id.addAddress);
    }

    // Uses user input to search for patient and if the user input matches more than one patient
//    then results page is launched otherwise user is taken directly to the patient page
    public void search(View view) {

        List<Patient> searchResults = new ArrayList<>();

        for (Patient p : patientList) {

            if (p.match(editTextSearch.getText().toString())) {
                searchResults.add(p);
            }
        }
        editTextSearch.setText("");
        if (searchResults.size() == 1) {
            goToPatientPage(searchResults, 0);

        } else if (searchResults.size() > 1) {
            Intent i = new Intent(HomePageActivity.this, PatResultsActivity.class);
            i.putExtra("pList", (Serializable) searchResults);
            startActivity(i);
        }
    }

    //Loads patient page
    private void goToPatientPage(List<Patient> p, int tag) {

        Intent i = new Intent(HomePageActivity.this, PatientActivity.class);

        i.putExtra("pList", (Serializable) p);
        i.putExtra("tag", tag);
        i.putExtra("prevPage", "home");
        startActivity(i);
    }

    // creates new patient based on information entered in text fields by user
    public void addPatient(View view) {

        Patient p = new Patient(editTextFName.getText().toString(), editTextLName.getText().toString(), editTextDob.getText().toString(), editTextAddress.getText().toString());

        patientList.add(p);
        editTextFName.setText("");
        editTextLName.setText("");
        editTextDob.setText("");
        editTextAddress.setText("");

        goToPatientPage(patientList, patientList.lastIndexOf(p));
    }

    // Reads patient information from csv file and builds a list of patients
    private void loadPatients() {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("PatientFile.csv")));

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                String[] x = mLine.split(",");
                Patient p = new Patient(x);
                patientList.add(p);
            }
        } catch (IOException e) {

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    // Creates default appointments for patients
    public static void loadAppointments(List<Patient> patientList) {

        Appointment a1 = new Appointment("10/10/2001", 1);
        Appointment a2 = new Appointment("01/11/2001", 2);
        Appointment a3 = new Appointment("30/11/2001", 3);
        Appointment a4 = new Appointment("28/12/2001", 4);
        patientList.get(0).appointments.add(a1);
        patientList.get(0).appointments.add(a2);
        patientList.get(0).appointments.add(a3);
        patientList.get(0).appointments.add(a4);


        a1 = new Appointment("10/02/2006", 1);
        a2 = new Appointment("01/03/2006", 2);
        a3 = new Appointment("30/04/2006", 3);
        a4 = new Appointment("28/05/2006", 4);
        patientList.get(1).appointments.add(a1);
        patientList.get(1).appointments.add(a2);
        patientList.get(1).appointments.add(a3);
        patientList.get(1).appointments.add(a4);


        a1 = new Appointment("10/09/2003", 1);
        a2 = new Appointment("01/10/2003", 2);
        patientList.get(2).appointments.add(a1);
        patientList.get(2).appointments.add(a2);


        a1 = new Appointment("10/02/2012", 1);
        a2 = new Appointment("01/03/2012", 2);
        a3 = new Appointment("30/04/2012", 3);
        patientList.get(3).appointments.add(a1);
        patientList.get(3).appointments.add(a2);
        patientList.get(3).appointments.add(a3);


        a1 = new Appointment("10/02/2006", 1);
        patientList.get(4).appointments.add(a1);
    }
}
