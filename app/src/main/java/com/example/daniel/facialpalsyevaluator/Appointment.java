package com.example.daniel.facialpalsyevaluator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Appointment implements Serializable {

    public String apDate;
    List<String> videos;
    String notes;
    public int apNum;
    String facograms;

    // The Facograms aren't actually used in the code as I don't have data to build them but I have code
    // to show how the system would deal with them


    public Appointment() {

        apDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        apNum = 1;
        videos = new ArrayList<>();
        notes = "";
        facograms = "";
    }

    public Appointment(String date, int number) {

        apDate = date;
        apNum = number;
        videos = new ArrayList<>();
        notes = "";
        facograms = "";
    }
}
