package com.example.naits.railly;

import java.util.Calendar;

/**
 * Created by naits on 23/12/2016.
 */

public class ConvertTime {

    public ConvertTime(){

    }

    public static int getDayFromString(String date){
        int day = 0;

        if(date.substring(0,1).equals("0")){
            day = Integer.parseInt(date.substring(1,2));
        }
        else{
            day = Integer.parseInt(date.substring(0,2));
        }

        return day;
    }

    public static int getMonthFromString(String date){
        int month = 0;

        if(date.substring(3,4).equals("0")){
            month = Integer.parseInt(date.substring(4,5));
        }
        else{
            month = Integer.parseInt(date.substring(3,5));
        }

        return month;
    }

    public static int getYearFromString(String date){
        return Integer.parseInt(date.substring(6));
    }

    public static int getHourFromString(String time){
        int hour = 0;

        if(time.substring(1,2).equals(":")){
            hour = Integer.parseInt(time.substring(0,1));
        }
        else{
            hour = Integer.parseInt(time.substring(0,2));
        }


        return hour;
    }

    public static int getMinuteFromString(String time){
        int minute = 0;

        if(time.substring(1,2).equals(":")){
            if(time.substring(2,3).equals("0")) {
                minute = Integer.parseInt(time.substring(3,4));
            }
            else{
                minute = Integer.parseInt(time.substring(2,4));
            }
        }
        else{
            if(time.substring(3,4).equals("0")) {
                minute = Integer.parseInt(time.substring(4,5));
            }
            else{
                minute = Integer.parseInt(time.substring(3,5));
            }
        }
        return minute;
    }
}
