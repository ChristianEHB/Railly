package com.example.naits.railly.DAO;

/**
 * Created by Ruben on 24/12/2016.
 */

import com.example.naits.railly.model.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.naits.railly.model.*;
import com.example.naits.railly.util.HttpHandler;

public class ConnectionDAO {
    private static final String CONNECTIONS_URL = "https://api.irail.be/connections/?to=";

    public static List<Connection> getConnections(String from, String to, String date, String time, String timeSel)
            throws Exception {
        String finalUrl = CONNECTIONS_URL + to + "&from=" + from + "&format=json" + "&date=" + date + "&time=" + time + "&timeSel=" + timeSel;
        return getConnections(finalUrl);
    }

    public static List<Connection> getConnections(String url) throws Exception {
        List<Connection> connections = new ArrayList<Connection>();
        try {
            //JSONObject jBase = new JSONObject(url);
            JSONObject jBase = new HttpHandler().getJSONObjectFromURL(url);
            if (jBase.has("error")) {
                throw new Exception(jBase.getString("message"));
            }

            JSONArray arrCon = jBase.getJSONArray("connection");


            for (int i = 0; i < arrCon.length(); i++) {
                JSONObject obj = (JSONObject) arrCon.get(i);
                Connection c = getConnection(obj);
                connections.add(c);
            }
        } catch (IOException io) {
            System.err.println("Error in StationDAO.loadCache()");
            io.printStackTrace();
        }
        return connections;
    }

    private static Connection getConnection(JSONObject obj) throws JSONException {
        int id = obj.getInt("id");
        int duration = obj.getInt("duration");
        Departure departure = DepartureDAO.getDeparture(obj.getJSONObject("departure"));
        Departure arrival = DepartureDAO.getDeparture(obj.getJSONObject("arrival"));
        return new Connection(id, duration, departure, arrival, ViaDAO.getVias(obj));
    }
}
