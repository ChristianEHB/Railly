package com.example.naits.railly;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private int hour, min;
    private int year, month, day;
    private AutoCompleteTextView textViewArrival, textViewDeparture;
    private ArrayAdapter<String> locationsAdapter;
    private List<String> stationList;
    private TextView textViewDate, textViewHour;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Ik heb deze code toegevoegd om te testen of ik kan pushen -Ruben
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set TextView To Current Time
        setCurrentTime();

        // Autocomplete for editTextFields
        stationList = Arrays.asList(getResources().getStringArray(R.array.stations));
        autoCompleteRoutePlanner();


    }





    private void autoCompleteRoutePlanner(){
        // Get the string array
        String[] stations = getResources().getStringArray(R.array.stations);

        // Create the adapter and set it to the AutoCompleteTextView
        textViewArrival = (AutoCompleteTextView) findViewById(R.id.autocomplete_arrival);
        textViewDeparture = (AutoCompleteTextView) findViewById(R.id.autocomplete_departure);

        locationsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stations);

        // Create the adapter and set it to the AutoCompleteTextView
        textViewArrival.setAdapter(locationsAdapter);
        textViewDeparture.setAdapter(locationsAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setCurrentTime(){
        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewDate.setText(setCurrentDate());
        textViewHour = (TextView) findViewById(R.id.textViewHour);
        textViewHour.setText(setCurrentHour());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String setCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        return String.format("%d/%d/%d",day, month + 1, year);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String setCurrentHour(){
        String newTime = null;
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR);
        min = calendar.get(Calendar.MINUTE);
        int AMorPM = calendar.get(Calendar.AM_PM);
        if(AMorPM == 1){
            hour += 12;
        }
        if(min < 10){
            newTime = String.format("%d:0%d", hour, min);
        }
        else{
            newTime = String.format("%d:%d", hour, min);
        }
        return newTime;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String newDate = null;
        month++;
        if(day < 10 && month < 10){
            newDate = String.format("0%d/0%d/%d", day, month, year);
        }
        else if(day == 10 && month < 10){
            newDate = String.format("%d/0%d/%d", day, month, year);
        }
        else if(day < 10 && month == 10){
            newDate = String.format("0%d/%d/%d", day, month, year);
        }
        else if(day < 10 && month > 10){
            newDate = String.format("0%d/%d/%d", day, month, year);
        }
        else if(day > 10 && month < 10){
            newDate = String.format("%d/0%d/%d", day, month, year);
        }
        else{
            newDate = String.format("%d/%d/%d", day, month, year);
        }
        textViewDate.setText(newDate);
    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int min) {
        String newTime = null;
        if(min < 10){
            newTime = String.format("%d:0%d", hour, min);
        }
        else{
            newTime = String.format("%d:%d", hour, min);
        }
        textViewHour.setText(newTime);
    }


    // Button Clicks

    protected void goToRouteScreen(View view){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    protected void goToStationScreen(View view){
        Intent i = new Intent(this, StationActivity.class);
        startActivity(i);
    }

    protected void goToTimePickerOnClick(View view){
        TimePickerDialog timePickerDialog = new TimePickerDialog(HomeActivity.this, HomeActivity.this, hour, min, android.text.format.DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void goToDatePickerOnClick(View view){
        DatePickerDialog datePickerDialog = new DatePickerDialog(HomeActivity.this, HomeActivity.this, year, month, day);
        datePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void searchOnClick(View view){
        if(checkIfInputIsCorrect(textViewDeparture.getText().toString()) == true && checkIfInputIsCorrect(textViewArrival.getText().toString()) == true
                && checkIfDateIsCorrect(textViewHour.getText().toString(), textViewDate.getText().toString()) == true){

            Intent i = new Intent(this, RouteActivity.class);
            /*
            Info doorgeven aan volgende activity
             */
            i.putExtra("departure", textViewDeparture.getText().toString());
            i.putExtra("arrival", textViewArrival.getText().toString());
            i.putExtra("date",textViewDate.getText().toString());
            i.putExtra("hour", textViewHour.getText().toString());

            startActivity(i);
        }
        else {
            if (checkIfDateIsCorrect(textViewHour.getText().toString(), textViewDate.getText().toString()) == false) {
                Toast.makeText(this, "Please fill in a correct time", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Please fill in the correct name of the station", Toast.LENGTH_LONG).show();
            }
        }
    }


    private boolean checkIfInputIsCorrect(String station){
        for(int i = 0;i<stationList.size();i++){
            if(station.equals(stationList.get(i))){
                return true;
            }
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean checkIfDateIsCorrect(String time, String date){
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);

        int currentHour = calendar.get(Calendar.HOUR);
        int currentMinute = calendar.get(Calendar.MINUTE);


        int day = HelpMethods.getDayFromString(date);
        int month = HelpMethods.getMonthFromString(date);
        int year = HelpMethods.getYearFromString(date);

        int hour = HelpMethods.getHourFromString(time);
        int minute = HelpMethods.getMinuteFromString(time);


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
