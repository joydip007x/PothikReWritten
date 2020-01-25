package com.example.atry;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAndTime extends Calendar {


    public static  String changeMonth(String s){


        return s;
    }
    public  static String getDate(){

        Date c = Calendar.getInstance().getTime();
        ///System.out.println("Current time => " + c);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return changeMonth(df.format(c));
       /// System.out.println(" XXXX "+ formattedDate.toString());
    }

    public static boolean diffIsTwo(String abc, String dateio) {


        int ad,am,ay, id,im,iy;

        ad= Integer.parseInt(abc.substring(0,2));
        am= Integer.parseInt(abc.substring(3,5));
        ay= Integer.parseInt(abc.substring(6,10));

        id= Integer.parseInt(dateio.substring(0,2));
        im= Integer.parseInt(dateio.substring(3,5));
        iy= Integer.parseInt(dateio.substring(6,10));

        if(ay!=iy) return  false;
        if(am==im) return  true;
        if(Math.abs(am-im)>1) return  false;

        if(  (id+31)-ad>29 ) return  false;
        return  true;

    }

    public static boolean backdated(String dateio) {



        int ad,am,ay, id,im,iy;

        String abc=getDate();
        ad= Integer.parseInt(abc.substring(0,2));
        am= Integer.parseInt(abc.substring(3,5));
        ay= Integer.parseInt(abc.substring(6,10));

        id= Integer.parseInt(dateio.substring(0,2));
        im= Integer.parseInt(dateio.substring(3,5));
        iy= Integer.parseInt(dateio.substring(6,10));

       // System.out.println(" WOW1 <"+dateio+">"+abc+"<<<<<"+ay);

        if(iy>ay) return  false;
        if(iy==ay){

             if(im>am) return  false;
             if(im==am){

                 if(id>ad){

                   ///  System.out.println(" WOW2 <"+iy+":"+ay+"><"+im+":"+am+"><"+ad+":"+id+">");
                     return  false;
                 }
             }
        }
     return  true;
    }

    @Override
    protected void computeTime() {

    }

    @Override
    protected void computeFields() {

    }

    @Override
    public void add(int field, int amount) {

    }

    @Override
    public void roll(int field, boolean up) {

    }

    @Override
    public int getMinimum(int field) {
        return 0;
    }

    @Override
    public int getMaximum(int field) {
        return 0;
    }

    @Override
    public int getGreatestMinimum(int field) {
        return 0;
    }

    @Override
    public int getLeastMaximum(int field) {
        return 0;
    }
}
