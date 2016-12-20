package com.example.naits.railly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DatePickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
    }

    protected void setDateOnClick(View view){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}
