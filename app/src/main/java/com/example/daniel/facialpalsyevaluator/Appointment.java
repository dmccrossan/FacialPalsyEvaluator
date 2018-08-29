package com.example.daniel.facialpalsyevaluator;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Appointment implements Serializable {

    String apDate;
    List<String> videos;
    List<String> notes;
    List<String> facograms;
    int apNum;

    public Appointment() {
        apDate = "";
        apNum = 1;
        videos = new ArrayList<>();
        notes = new ArrayList<>();
        facograms = new ArrayList<>();
    }

    public Appointment(String date, int number) {


        apDate = date;
        apNum = number;
        videos = new ArrayList<>();
        notes = new ArrayList<>();
        facograms = new ArrayList<>();
    }
}
