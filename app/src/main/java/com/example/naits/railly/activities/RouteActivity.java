package com.example.naits.railly.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.naits.railly.DAO.ConnectionDAO;
import com.example.naits.railly.DAO.StationDAO;
import com.example.naits.railly.R;
import com.example.naits.railly.model.Connection;
import com.example.naits.railly.model.Route;
import com.example.naits.railly.adapters.RouteListAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.naits.railly.util.HttpHandler;

public class RouteActivity extends AppCompatActivity {

    private ListView lvRoute;
    private RouteListAdapter adapter;
    private List<Route> routeList;


    private String arrival, departure, date, hour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        //retrieve data from prev activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            arrival = extras.getString("arrival").toString();
            departure = extras.getString("departure").toString();
            date = extras.getString("date").toString();
            hour = extras.getString("hour").toString();
        }



        //TODO: have task get url
        new AsyncRouteFetch().execute(setUrl(departure, arrival, hour, date));




    }

    //TODO: Put this in connectionDAO
    private String setUrl(String departure, String arrival, String time, String date) {
        String hour = time.substring(0, 2);
        String min = time.substring(3);

        String day = date.substring(0, 2);
        String month = date.substring(3, 5);
        String year = date.substring(8, 10);
        //example "https://api.irail.be/connections/?to=Aalst&from=Londerzeel&date=201216&time=1930&timeSel=arrive&format=json";
        return String.format("https://api.irail.be/connections/?to=%s&from=%s&date=%s%s%s&time=%s%s&timeSel=arrive&format=json", departure, arrival, day, month, year, hour, min);
    }


    // Button Clicks

    protected void goToRouteScreen(View view) {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    protected void goToStationScreen(View view) {
        Intent i = new Intent(this, StationActivity.class);
        startActivity(i);
    }

    private class AsyncRouteFetch extends AsyncTask<String, Integer, ArrayList<Connection>> {

        ArrayList<Connection> arrCon = null;

        public AsyncRouteFetch() {

        }

        @Override
        protected ArrayList<Connection> doInBackground(String... param) {
            ConnectionDAO conDAO = new ConnectionDAO();

            try {
                JSONObject JOstatCache = new HttpHandler().getJSONObjectFromStream(getResources().openRawResource(R.raw.stationcache));
                new StationDAO().loadCache(JOstatCache);

                arrCon = (ArrayList<Connection>) conDAO.getConnections(param[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }




            return arrCon;
        }

        @Override
        protected void onPostExecute(ArrayList<Connection> ce){

            lvRoute = (ListView) findViewById(R.id.listView_routes);

            routeList = new ArrayList<>();

            adapter = new RouteListAdapter(getApplicationContext(), routeList);
            lvRoute.setAdapter(adapter);

            lvRoute.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), "Clicked on route = " + view.getTag(), Toast.LENGTH_LONG).show();
                }
            });

            if(arrCon != null){
                for(Connection c : arrCon){
                    int i = 1;
                    Log.d("routeactivity:", c.toString());
                    routeList.add(new Route(i, c.getDeparture().toString(), c.getArrival().toString()));
                    i++;
                }

            }
        }


    }

}
