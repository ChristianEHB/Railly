package com.example.naits.railly;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

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

    protected void goToDatePickerOnClick(View view){
        Intent i = new Intent(this, DatePickerActivity.class);
        startActivity(i);
    }





    private void autoCompleteRoutePlanner(){
        // Get the string array
        String[] stations = getResources().getStringArray(R.array.stations);

        // Create the adapter and set it to the AutoCompleteTextView
        AutoCompleteTextView textViewArrival = (AutoCompleteTextView) findViewById(R.id.autocomplete_arrival);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_departure);

        ArrayAdapter<String> adapterArrival = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stations);
        ArrayAdapter<String> adapterDeparture = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stations);

        // Create the adapter and set it to the AutoCompleteTextView
        textViewArrival.setAdapter(adapterArrival);
        textView.setAdapter(adapterDeparture);
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setCurrentTime(){
        TextView textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewDate.setText(setCurrentDate());
        TextView textViewHour = (TextView) findViewById(R.id.textViewHour);
        textViewHour.setText(setCurrentHour());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String setCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return String.format("%d/%d/%d",day, month, year);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String setCurrentHour(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        int AMorPM = calendar.get(Calendar.AM_PM);
        if(AMorPM == 1){
            hour += 12;
        }
        return String.format("%d:%d", hour, min);
    }

}
