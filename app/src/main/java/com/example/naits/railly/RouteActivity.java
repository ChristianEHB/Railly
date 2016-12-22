package com.example.naits.railly;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Api.HttpHandler;

public class RouteActivity extends AppCompatActivity {

    private ListView lvRoute;
    private RouteListAdapter adapter;
    private List<Route> routeList;
    private HttpHandler handler;


    private String arrival, departure, date, hour;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        lvRoute = (ListView)findViewById(R.id.listView_routes);

        routeList = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            arrival = extras.getString("arrival").toString();
            departure = extras.getString("departure").toString();
            date = extras.getString("date").toString();
            hour = extras.getString("hour").toString();
        }

        // add data from api here

        routeList.add(new Route(1, "Brussel-Zuid", "Antwerpen-Centraal"));
        routeList.add(new Route(2, "Brussel-Noord", "Mechelen"));
        routeList.add(new Route(3, "Brugge", "Antwerpen-Centraal"));
        routeList.add(new Route(4, "Brussel-Zuid", "Brussel-Noord"));
        routeList.add(new Route(5, "Brussel-Noord", "Antwerpen-Centraal"));
        routeList.add(new Route(6, "Mechelen", "Brussel-Noord"));
        routeList.add(new Route(7, "Brussel-Zuid", "Antwerpen-Centraal"));

        adapter = new RouteListAdapter(getApplicationContext(),routeList);
        lvRoute.setAdapter(adapter);

        lvRoute.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // do something
                Toast.makeText(getApplicationContext(), "Clicked on route = " + view.getTag(),Toast.LENGTH_LONG).show();
            }
        });

        handler = new HttpHandler();
        Log.d("test" , setUrl(departure, arrival, hour, date));
        //Log.d("http req", "onCreate: " + handler.makeServiceCall(setUrl(departure, arrival, hour, date)));

        Log.d("JSON",handler.execute(setUrl(departure, arrival, hour, date)).toString());
    }


    private String setUrl(String departure, String arrival, String time, String date){
        String hour = time.substring(0,1);
        String min = time.substring(3);

        String day = date.substring(0,1);
        String month = date.substring(3,4);
        String year = date.substring(6,7);
        return "https://api.irail.be/connections/?to=Aalst&from=Londerzeel&date=201216&time=1930&timeSel=arrive&format=json";
        //return String.format("https://api.irail.be/connections/?to=%s&from=%s&date=%s%s%s&time=%s%s&timeSel=arrive",departure,arrival,day,month,year,hour,min);
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
