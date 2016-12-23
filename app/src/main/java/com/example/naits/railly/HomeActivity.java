package com.example.naits.railly;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import static com.example.naits.railly.ConvertTime.formatDate;
import static com.example.naits.railly.ConvertTime.formatTime;
import static com.example.naits.railly.ConvertTime.setTo24Hour;
import static com.example.naits.railly.HelpMethods.checkIfDateIsCorrect;
import static com.example.naits.railly.HelpMethods.checkIfInputIsCorrect;

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
        return formatDate(year,month,day);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String setCurrentHour(){
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR);
        min = calendar.get(Calendar.MINUTE);
        int AMorPM = calendar.get(Calendar.AM_PM);
        hour = setTo24Hour(hour, AMorPM);
        return formatTime(hour, min);
    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        textViewDate.setText(formatDate(year,month,day));
    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int min) {
        textViewHour.setText(formatTime(hour,min));
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
        if(checkIfInputIsCorrect(textViewDeparture.getText().toString(), stationList) == true
                && checkIfInputIsCorrect(textViewArrival.getText().toString(), stationList) == true
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
}
