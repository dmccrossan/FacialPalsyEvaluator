package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//import daniel.example.com.facialpalsyevaluator.LoginActivity;
import daniel.example.com.facialpalsyevaluator.R;


public class HomePageActivity extends AppCompatActivity {

    List<Patient> patientList = new ArrayList();
    String filePath;

    EditText editTextSearch;
    EditText editTextFName;
    EditText editTextLName;
    EditText editTextDob;
    EditText editTextAddress;

    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            Intent i = getIntent();
            patientList = (List<Patient>) i.getSerializableExtra("pList");

            if (patientList == null) {
                patientList = new ArrayList();
                setup();
            }


        setContentView(R.layout.activity_home_page);

        // mStorageRef = FirebaseStorage.getInstance().getReference();

//         filePath = getString(R.string.filePath);
        //   patientList = loadPatients();


        editTextSearch = findViewById(R.id.searchPatient);
        editTextFName = findViewById(R.id.addFName);
        editTextLName = findViewById(R.id.addLName);
        editTextDob = findViewById(R.id.addDob);
        editTextAddress = findViewById(R.id.addAddress);
    }

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

    private void goToPatientPage(List<Patient> p, int tag) {
        Intent i = new Intent(HomePageActivity.this, PatientActivity.class);

        i.putExtra("pList", (Serializable) p);
        i.putExtra("tag", tag);
        i.putExtra("prevPage", "home");
        startActivity(i);
    }

    public void addPatient(View view) {

        Patient p = new Patient(editTextFName.getText().toString(), editTextLName.getText().toString(), editTextDob.getText().toString(), editTextAddress.getText().toString());

        patientList.add(p);
        editTextFName.setText("");
        editTextLName.setText("");
        editTextDob.setText("");
        editTextAddress.setText("");

        goToPatientPage(patientList, patientList.lastIndexOf(p));
        // might need to be changed to be the size of the list

    }

    private ArrayList<Patient> loadPatients() {

        ArrayList<Patient> patientList = new ArrayList();

        try {
            Scanner scanner = new Scanner(new File(filePath));
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String x = scanner.next();
                Patient p = new Patient(x);
                Log.d("myTag", x);
                patientList.add(p);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            Log.d("myTag", e.toString());
        }

        return patientList;
    }

    private void setup() {
        String x = "0000000001,Paul,Simon,03/06/1979,10 Hope Street";
        Patient p = new Patient(x);
        Appointment a1 = new Appointment("10/10/2001", 1);
        Appointment a2 = new Appointment("01/11/2001", 2);
        Appointment a3 = new Appointment("30/11/2001", 3);
        Appointment a4 = new Appointment("28/12/2001", 4);
        p.appointments.add(a1);
        p.appointments.add(a2);
        p.appointments.add(a3);
        p.appointments.add(a4);
        patientList.add(p);


        x = "0000000002,Sam,Smith,15/08/1999,101 Hope Lane";
        p = new Patient(x);
        a1 = new Appointment("10/02/2006", 1);
        a2 = new Appointment("01/03/2006", 2);
        a3 = new Appointment("30/04/2006", 3);
        a4 = new Appointment("28/05/2006", 4);
        p.appointments.add(a1);
        p.appointments.add(a2);
        p.appointments.add(a3);
        p.appointments.add(a4);
        patientList.add(p);


        x = "0000000011,Mean Mr.,Mustard,26/09/1969,11 Abbey Road";
        p = new Patient(x);
        a1 = new Appointment("10/09/2003", 1);
        a2 = new Appointment("01/10/2003", 2);
        a3 = new Appointment("30/11/2003", 3);
        a4 = new Appointment("28/12/2003", 4);
        p.appointments.add(a1);
        p.appointments.add(a2);
        p.appointments.add(a3);
        p.appointments.add(a4);
        patientList.add(p);


        x = "0000000004,Scott,Scott,09/10/2000,27 Hill Street";
        p = new Patient(x);
        a1 = new Appointment("10/02/2012", 1);
        a2 = new Appointment("01/03/2012", 2);
        a3 = new Appointment("30/04/2012", 3);
        a4 = new Appointment("28/05/2012", 4);
        p.appointments.add(a1);
        p.appointments.add(a2);
        p.appointments.add(a3);
        p.appointments.add(a4);
        patientList.add(p);


        x = "1234567896,Kirk,Xavier,25/12/1889,0 A Way";
        p = new Patient(x);
        a1 = new Appointment("10/02/2006", 1);
        a2 = new Appointment("01/03/2006", 2);
        a3 = new Appointment("30/04/2006", 3);
        a4 = new Appointment("28/05/2006", 4);
        p.appointments.add(a1);
        p.appointments.add(a2);
        p.appointments.add(a3);
        p.appointments.add(a4);
        patientList.add(p);
    }

}
