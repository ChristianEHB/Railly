package com.example.naits.railly;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import Api.HttpHandler;

public class StationActivity extends AppCompatActivity {

    private String TAG = StationActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    private static String url = "https://api.irail.be/liveboard/?station=Londerzeel&fast=true&format=json";

    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);

        contactList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        // hier nog code lijn
    }

    private class GetContacts extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(StationActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler sh = new HttpHandler();

            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if(jsonStr != null){
                try{
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONObject liveboard = jsonObj.getJSONObject("liveboard");
                    String version = liveboard.getString("version");
                    String timestamp = liveboard.getString("timestamp");
                    String station = liveboard.getString("station");

                    JSONObject stationinfo = liveboard.getJSONObject("stationinfo");
                    String id = stationinfo.getString("id");
                    String locationX = stationinfo.getString("locationX");
                    String locationY = stationinfo.getString("locationY");
                    String idd = stationinfo.getString("@id");
                    String standardname = stationinfo.getString("standardname");
                    String name = stationinfo.getString("name");
                    String empty = liveboard.getString("empty");





                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            }
            else{
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }


            return null;
        }
    }

    protected void onPostExecute(Void result) {
        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();
        /**
         * Updating parsed JSON data into ListView
         * */
        ListAdapter adapter = new SimpleAdapter(
                StationActivity.this, contactList,
                R.layout.item_route_list, new String[]{"locationX", "locationY",
        }, new int[]{R.id.route_item_arrival,
                R.id.route_item_departure});

        lv.setAdapter(adapter);
    }

}
