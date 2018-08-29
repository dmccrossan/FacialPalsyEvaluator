package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import daniel.example.com.facialpalsyevaluator.LoginActivity;
import daniel.example.com.facialpalsyevaluator.R;


public class HomePageActivity extends AppCompatActivity {

    List<Patient> patientList = new ArrayList();
    String filePath ;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

      //  mContext = getApplicationContext();
         filePath = getString(R.string.filePath);


     //   patientList = loadPatients();

        String  x = "000000001,Paul,Simon,03/06/1979,10 Hope Street";
        Patient p = new Patient(x);

        patientList.add(p);
        x = "000000002,Sam,Smith,15/08/1999,101 Hope Lane";
         p = new Patient(x);

        patientList.add(p);
          x = "000000011,Mean Mr.,Mustard,26/09/1969,11 Abbey Road";
         p = new Patient(x);

        patientList.add(p);
         x = "000000004,Scott,Scott,09/10/2000,27 Hill Street";
         p = new Patient(x);

        patientList.add(p);
         x = "1234567896,Kirk,Xavier,25/12/1889,0 A Way";
         p = new Patient(x);

        patientList.add(p);

        editText =  findViewById(R.id.searchPatient);
    }

   /*
    public void onClick(View v) {
        editText.getText().clear(); //or you can use editText.setText("");
    }
*/

    public void search (View view){

        List<Patient> searchResults = new ArrayList<>();

        for (Patient p : patientList){

            if(p.match(editText.getText().toString())){searchResults.add(p);}
        }


        if (searchResults.size()==1 ){
            Intent i = new Intent(HomePageActivity.this, PatientActivity.class);

            i.putExtra("key",searchResults.get(0));
            startActivity(i);

        }
        else if(searchResults.size()>1) {
            Intent i = new Intent(HomePageActivity.this, PatResultsActivity.class);
            i.putExtra("key", (Serializable) searchResults);
            startActivity(i);
        }

        }

        private ArrayList<Patient> loadPatients(){

            ArrayList<Patient> patientList = new ArrayList();


            try {
                Scanner scanner =  new Scanner(new File(filePath));
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    String  x = scanner.next();
                    Patient p = new Patient(x);
                    Log.d("myTag", x);
                    patientList.add(p);
                }
                scanner.close();
            }

            catch (FileNotFoundException e){ Log.d("myTag", e.toString());}

            return patientList;
        }

}
