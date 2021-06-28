package com.example.coursiset.data;

import com.example.coursiset.model.Cours;

import java.util.ArrayList;

public class coursdata {
    private String [] coursname={"course1","cours2","course3","course4","course5","course6"};
    public ArrayList<Cours>coursArrayList(){
        ArrayList<Cours> list=new ArrayList<>();
        for (int i=0;i<coursname.length;i++){
            Cours cours=new Cours(coursname[i],coursname[i],coursname[i]);
            list.add(cours);

        }
        return list;
    }
}
