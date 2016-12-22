package com.example.naits.railly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LiveBoardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_boards);
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
}
