package com.example.naits.railly.util;

import java.util.Calendar;

/**
 * Created by naits on 23/12/2016.
 */

public class ConvertTime {

    public ConvertTime(){

    }


    // Methods for the time


    public static int setTo24Hour(int hour, int AMorPM){
        if(AMorPM == 1){
            hour += 12;
        }
        return hour;
    }

    public static String setCurrentHour(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        int AMorPM = calendar.get(Calendar.AM_PM);
        hour = setTo24Hour(hour, AMorPM);
        return formatTime(hour, min);
    }

    public static String setCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return formatDate(year,month,day);
    }

    public static String formatDate(int year, int month, int day){
        month++;
        if(day < 10 && month < 10){
            return String.format("0%d/0%d/%d", day, month, year);
        }
        else if(day == 10 && month < 10){
            return String.format("%d/0%d/%d", day, month, year);
        }
        else if(day < 10 && month == 10){
            return String.format("0%d/%d/%d", day, month, year);
        }
        else if(day < 10 && month > 10){
            return String.format("0%d/%d/%d", day, month, year);
        }
        else if(day > 10 && month < 10){
            return String.format("%d/0%d/%d", day, month, year);
        }
        else{
            return String.format("%d/%d/%d", day, month, year);
        }
    }


    public static String formatTime(int hour, int minute){
        if(minute < 10){
            return String.format("%d:0%d", hour, minute);
        }
        else{
            return String.format("%d:%d", hour, minute);
        }
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
