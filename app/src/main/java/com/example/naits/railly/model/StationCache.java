package com.example.naits.railly.model;

import java.util.ArrayList;
import java.util.List;

public class StationCache {
    private List<Station> stations;
    private static StationCache instance;

    private StationCache() {
        stations = new ArrayList<Station>();


    }

    public static StationCache getInstance() {
        if (instance == null) instance = new StationCache();
        return instance;
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public List<Station> getStations() {
        return stations;
    }

    public String[] getStationsNames() {
        String[] arr = new String[stations.size()];
        for (int i = 0; i < stations.size(); i++)
            arr[i] = stations.get(i).getName();
        return arr;
    }

    public Station getStationWithID(String id) {
        for (Station st : stations) {
            if (st.getId().equals(id))
                return st;
        }
        return null;
    }

    public Station getStationWithName(String name) {
        for (Station st : stations) {
            if (st.getName().equals(name))
                return st;
        }
        return null;
    }

    public String getFormattedIDByStationName(String stationName) {
        for (Station st : stations) {
            if (st.getName().equals(stationName))
                return st.getFormattedID();
        }
        return null;
    }

}
