package com.example.naits.railly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AutoCompleteTextView textViewArrival = (AutoCompleteTextView) findViewById(R.id.autocomplete_arrival);
        // Get the string array
        String[] stations = getResources().getStringArray(R.array.stations);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapterArrival = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stations);
        textViewArrival.setAdapter(adapterArrival);

        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_departure);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapterDeparture = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stations);
        textView.setAdapter(adapterDeparture);
    }

    protected void searchOnClick(View view){
        Intent i = new Intent(this, RouteActivity.class);
        startActivity(i);
    }
}
