package com.example.naits.railly.DAO;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.naits.railly.model.*;
import com.example.naits.railly.util.HttpHandler;

public class StationDAO {

    private static final String ALL_STATIONS_URL = "https://irail.be/stations/NMBS";


    public static void loadCache(JSONObject jBase) throws JSONException, IOException {
        StationCache cache = StationCache.getInstance();
        String url = new HttpHandler().makeServiceCall(ALL_STATIONS_URL);


        JSONArray jGraph = jBase.getJSONArray("@graph");

            /*jGraph.forEach(new Consumer<Object>() {
                @Override
				public void accept(Object t) {
					cache.addStation(getStation((JSONObject)t));
				}
			});*/

        for (int i = 0; i < jGraph.length(); i++) {
            JSONObject obj = (JSONObject) jGraph.get(i);
            cache.addStation(getStation(obj));
        }
    }

    private static Station getStation(JSONObject arr) throws JSONException {
        String name = arr.getString("name");
        String id = arr.getString("@id");
        String country = arr.getString("country");
        String avg = arr.getString("avgStopTimes");
        double lat = arr.getDouble("latitude");
        double lon = arr.getDouble("longitude");
        Station station = new Station(name, id, country, avg, lat, lon);
        if (arr.has("alternative")) {
            JSONArray alternative = arr.getJSONArray("alternative");
            station.setAlternativeStationNames(getAlternativeStationName(alternative));
        }

        return station;
    }

    private static List<AlternativeStationName> getAlternativeStationName(JSONArray alt) throws JSONException {
        List<AlternativeStationName> alternatives = new ArrayList<AlternativeStationName>();
        /*alt.forEach(new Consumer<Object>() {
            @Override
            public void accept(Object t) {
                JSONObject obj = (JSONObject) t;
                String value = obj.getString("@value"), language = obj.getString("@language");
                alternatives.add(new AlternativeStationName(value, language));
            }
        });*/

        for (int i = 0; i < alt.length(); i++) {
            JSONObject obj = (JSONObject) alt.get(i);
            String value = obj.getString("@value"), language = obj.getString("@language");
            alternatives.add(new AlternativeStationName(value, language));
        }
        return alternatives;
    }
}
