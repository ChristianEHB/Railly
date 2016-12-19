package com.example.naits.railly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    protected void searchOnClick(View view){
        Intent i = new Intent(this, RouteActivity.class);
        startActivity(i);
    }
}
