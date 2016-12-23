package com.example.naits.railly;

import java.util.Calendar;
import java.util.List;

/**
 * Created by naits on 23/12/2016.
 */

public class HelpMethods {

    public static boolean checkIfInputIsCorrect(String station, List<String> stationList){
        for(int i = 0;i<stationList.size();i++){
            if(station.equals(stationList.get(i))){
                return true;
            }
        }
        return false;
    }

    public static boolean checkIfDateIsCorrect(String time, String date){
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);

        int AMorPM = calendar.get(Calendar.AM_PM);
        int currentHour = calendar.get(Calendar.HOUR);
        int currentMinute = calendar.get(Calendar.MINUTE);

        if(AMorPM == 1){
            currentHour += 12;
        }


        int day = ConvertTime.getDayFromString(date);
        int month = ConvertTime.getMonthFromString(date);
        int year = ConvertTime.getYearFromString(date);

        int hour = ConvertTime.getHourFromString(time);
        int minute = ConvertTime.getMinuteFromString(time);


        if(year < currentYear){
            return false;
            // 2015 < 2016
        }

        if(year == currentYear && month < currentMonth){
            return false;
            // 2016 == 2016 && november < december
        }

        if(month == currentMonth && day < currentDay){
            return false;
            // december == december && 21 < 23
        }

        if(day == currentDay && hour < currentHour){
            return false;
            // 23 == 23 && 15 < 16
        }

        if(hour == currentHour && minute < currentMinute){
            return false;
            // 16 == 16 && 30 < 50
        }

        return true;
    }
}
