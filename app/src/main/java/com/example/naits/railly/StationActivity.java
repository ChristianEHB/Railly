package com.example.naits.railly;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Api.HttpHandler;

public class StationActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private List<String> stationList;
    private ArrayAdapter<String> locationsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);

        stationList = Arrays.asList(getResources().getStringArray(R.array.stations));
        autoCompleteRoutePlanner();


    }


    private void autoCompleteRoutePlanner(){
        // Get the string array
        String[] stations = getResources().getStringArray(R.array.stations);

        // Create the adapter and set it to the AutoCompleteTextView
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autocomplete_station);
        locationsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stations);

        // Create the adapter and set it to the AutoCompleteTextView
        autoCompleteTextView.setAdapter(locationsAdapter);
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

    protected void searchStationOnClick(View v){

        if(checkIfInputIsCorrect(autoCompleteTextView.getText().toString()) == true){
            Intent i = new Intent(this, LiveBoardsActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this, "Please fill in the correct name of the station", Toast.LENGTH_SHORT).show();
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
}

