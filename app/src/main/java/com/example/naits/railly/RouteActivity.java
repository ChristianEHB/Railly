package com.example.naits.railly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RouteActivity extends AppCompatActivity {

    private ListView lvRoute;
    private RouteListAdapter adapter;
    private List<Route> routeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        lvRoute = (ListView)findViewById(R.id.listView_routes);

        routeList = new ArrayList<>();

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
    }
}
