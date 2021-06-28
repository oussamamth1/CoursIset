package com.example.coursiset.model;

import android.content.Context;

public class Cours {

    private String coursname;
    private String coursImag;
    private String coursAuthImag;
    public Cours(String coursname, String coursImag, String coursAuthImag) {
        this.coursname = coursname;
        this.coursImag = coursImag;
        this.coursAuthImag = coursAuthImag;
    }
  public int getImageResourcid(Context context){
        return context.getResources().getIdentifier(coursImag,"drawable",context.getPackageName());
    }
    public int getImageResourcidaut(Context context){
        return context.getResources().getIdentifier(coursImag,"drawable",context.getPackageName());
    }

    public String getcoursname() {
        return coursname;
    }

    public void setcoursname(String coursname) {
        this.coursname = coursname;
    }

    public String getcoursImag() {
        return coursImag;
    }

    public void setcoursImag(String coursImag) {
        this.coursImag = coursImag;
    }

    public String getcoursAuthImag() {
        return coursAuthImag;
    }

    public void setcoursAuthImag(String coursAuthImag) {
        this.coursAuthImag = coursAuthImag;
    }

}
