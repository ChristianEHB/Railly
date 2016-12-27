package com.example.naits.railly.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.naits.railly.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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

    protected void goToAboutScreen(View view){
        Intent i = new Intent(this, AboutActivity.class);
        startActivity(i);
    }


}
