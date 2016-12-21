package com.example.naits.railly;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private int hour, min;
    private int year, month, day;
    private  AutoCompleteTextView textViewArrival, textViewDeparture;
    private ArrayAdapter<String> locationsAdapter;
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
        autoCompleteRoutePlanner();



    }


    protected void searchOnClick(View view){
        Intent i = new Intent(this, RouteActivity.class);
        startActivity(i);
    }
    protected void changeLanguageToEnglishOnClick(View view){
        Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
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
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        return String.format("%d/%d/%d",day, month, year);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String setCurrentHour(){
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR);
        min = calendar.get(Calendar.MINUTE);
        int AMorPM = calendar.get(Calendar.AM_PM);
        if(AMorPM == 1){
            hour += 12;
        }
        return String.format("%d:%d", hour, min);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void goToDatePickerOnClick(View view){
        DatePickerDialog datePickerDialog = new DatePickerDialog(HomeActivity.this, HomeActivity.this, year, month, day);
        datePickerDialog.show();

    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }
}
