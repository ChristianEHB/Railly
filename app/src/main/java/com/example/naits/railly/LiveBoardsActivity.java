package com.example.naits.railly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class LiveBoardsActivity extends AppCompatActivity {

    private ListView lvLiveBoard;
    private LiveBoardListAdapter adapter;
    private List<Route> routeList;

    private String time, destination, platform, canceled, delay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_boards);

        lvLiveBoard = (ListView) findViewById(R.id.listView_liveboard);

        routeList = new ArrayList<>();

        routeList.add(new Route(1, "17:45", "Brussel-Zuid", "12", "0", "12" ));
        routeList.add(new Route(2, "18:45", "Brussel-Zuid", "1", "0", "0" ));
        routeList.add(new Route(3, "19:45", "Brussel-Noord", "6", "1", "5" ));
        routeList.add(new Route(4, "20:45", "Brussel-Zuid", "1", "1", "5" ));
        routeList.add(new Route(5, "21:45", "Brussel-Noord", "4", "0", "0" ));

        adapter = new LiveBoardListAdapter(getApplicationContext(), routeList);
        lvLiveBoard.setAdapter(adapter);
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
