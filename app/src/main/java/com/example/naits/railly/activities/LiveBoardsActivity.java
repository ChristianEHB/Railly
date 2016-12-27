package com.example.naits.railly.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.naits.railly.DAO.DepartureDAO;
import com.example.naits.railly.DAO.StationDAO;
import com.example.naits.railly.adapters.LiveBoardListAdapter;
import com.example.naits.railly.R;
import com.example.naits.railly.model.Departure;
import com.example.naits.railly.model.DepartureInfo;
import com.example.naits.railly.model.Route;
import com.example.naits.railly.model.Station;
import com.example.naits.railly.model.StationCache;
import com.example.naits.railly.util.DateUtil;
import com.example.naits.railly.util.HttpHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LiveBoardsActivity extends AppCompatActivity {

    private ListView lvLiveBoard;
    private LiveBoardListAdapter adapter;
    private List<Route> routeList;

    private String stationString,time, destination, platform, canceled, delay;

    protected ProgressDialog progDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_boards);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            stationString = extras.getString("station").toString();
        }

        routeList = new ArrayList<>();

        new AsyncInitLiveBoard().execute(stationString);


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

    private class AsyncInitLiveBoard extends AsyncTask<String, Integer, Void> {

        protected List<Departure> departures = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDialog = new ProgressDialog(LiveBoardsActivity.this);
            progDialog.setMessage("Loading...");
            progDialog.setIndeterminate(false);
            progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDialog.setCancelable(true);
            progDialog.show();
        }


        @Override
        protected Void doInBackground(String... params) {

            departures = new ArrayList<Departure>();

            try {
                if(StationCache.getInstance().getStationsNames().length == 0) {
                    JSONObject JOstatCache = new HttpHandler().getJSONObjectFromStream(getResources().openRawResource(R.raw.stationcache));
                    new StationDAO().loadCache(JOstatCache);
                }
                Station station =  StationCache.getInstance().getStationWithName(stationString);
                if(station != null) { //Kan gebeuren door slechte overeenkomst voorgestelde stationsnamen en die uit de cache, moet gefixed
                    departures = new DepartureDAO().getDeparturesFrom(station);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void unused){
            progDialog.dismiss();

            if (departures != null){
                if(departures.size() == 0){
                    AlertDialog alertDialog = new AlertDialog.Builder(LiveBoardsActivity.this).create();
                    alertDialog.setTitle("Sorry!");
                    alertDialog.setMessage("No departures could be found.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else{
                    //Convert departures to route
                    //TODO: Change liveboardlistadapter to accept departure objects

                    ArrayList<Route> routes = new ArrayList<Route>();

                    for(Departure d : departures){
                        DepartureInfo di = d.getDepartureInfo();
                        int i = 0;
                        String arrivalTime = DateUtil.timeStampToDate(d.getDepartureInfo().getTimeStamp()).substring(11);//discard date from string
                        String destination = d.getDirection();
                        String platform = di.getPlatform().getName();
                        String canceled = String.valueOf(di.isCanceled());
                        String delay = String.valueOf(di.getDelay());

                        Route r = new Route(i,arrivalTime,destination,platform,canceled,delay);
                        routes.add(r);
                        i++;
                    }
                    lvLiveBoard = (ListView) findViewById(R.id.listView_liveboard);
                    adapter = new LiveBoardListAdapter(getApplicationContext(), routes);
                    lvLiveBoard.setAdapter(adapter);
                }
            }
        }
    }

}
