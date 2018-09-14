package com.example.daniel.facialpalsyevaluator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
Creates patient class - details all the attributes and methods of each patient
 */
public class Patient implements Serializable {

    public String address;
    public String chi;
    public String fname;
    public String lname;
    public String dob;
    public List<Appointment> appointments;

    public Patient() {
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L; // creates a random 10 digit number
        chi = String.valueOf(number); // stores the random number as the chi number
        fname = "";
        lname = "";
        dob = "";
        address = "";
        appointments = new ArrayList<>();
    }

    public Patient(String fname, String lname, String dob, String address) {

        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        chi = String.valueOf(number);

        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.address = address;
        appointments = new ArrayList<>();
    }

    public Patient(String[] tempData) {

        chi = tempData[0];
        fname = tempData[1];
        lname = tempData[2];
        dob = tempData[3];
        address = tempData[4];
        appointments = new ArrayList<>();
    }

    // Builds a string list of patient details
    public List<String> toList() {

        List<String> dataList = new ArrayList<>();
        dataList.add(fname);
        dataList.add(lname);
        dataList.add(dob);
        dataList.add(address);

        return dataList;
    }

    // Checks if the input string is contained within one of the attributes
    public boolean match(String input) {
        return chi.contains(input) || fname.toLowerCase().contains(input.toLowerCase()) || lname.toLowerCase().contains(input.toLowerCase()) || address.toLowerCase().contains(input.toLowerCase()) || dob.toLowerCase().contains(input.toLowerCase());
    }
}
