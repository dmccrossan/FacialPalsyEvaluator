package com.example.daniel.facialpalsyevaluator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person implements Serializable {

    String address;
    String chi;
    String fname;
    String lname;
    String dob;

    public Patient (){

    }

    public Patient (String data){

        String[] tempData = data.split(",");
        chi     = tempData[0];
        fname   = tempData[1];
        lname   = tempData[2];
        dob     = tempData[3];
        address = tempData[4];

    }

    public List<String> toList() {
        List<String> dataList = new ArrayList<>();
       // dataList.add(chi);
        dataList.add(fname);
        dataList.add(lname);
        dataList.add(dob);
        dataList.add(address);

        return dataList;
    }

    public boolean match (String input) {
        return chi.contains(input) || fname.toLowerCase().contains(input.toLowerCase()) || lname.toLowerCase().contains(input.toLowerCase()) || address.toLowerCase().contains(input.toLowerCase()) || dob.toLowerCase().contains(input.toLowerCase()) ;
    }
}
