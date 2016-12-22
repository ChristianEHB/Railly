package com.example.naits.railly.nmbs.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONObject;

import com.school.project.nmbs.model.AlternativeStationName;
import com.school.project.nmbs.model.Station;
import com.school.project.nmbs.model.StationCache;
import com.school.project.util.NetUtil;

public class StationDAO {

	private static final String ALL_STATIONS_URL = "https://irail.be/stations/NMBS";

	public static void loadCache() throws org.json.JSONException {
		try {
			StationCache cache = StationCache.getInstance();
			String url = NetUtil.curlURL(ALL_STATIONS_URL);
			JSONObject jBase = new JSONObject(url);
			JSONArray jGraph = jBase.getJSONArray("@graph");
			jGraph.forEach(new Consumer<Object>() {
				@Override
				public void accept(Object t) {
					cache.addStation(getStation((JSONObject)t));
				}
			});
		} catch (IOException io) {
			System.err.println("Error in StationDAO.loadCache()");
			io.printStackTrace();
		}
	}
	
	private static Station getStation(JSONObject arr) throws org.json.JSONException{
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
	
	private static List<AlternativeStationName> getAlternativeStationName(JSONArray alt) {
		List<AlternativeStationName> alternatives = new ArrayList<AlternativeStationName>();
		alt.forEach(new Consumer<Object>() {
			@Override
			public void accept(Object t) {
				JSONObject obj = (JSONObject)t;
				String value = obj.getString("@value"), language = obj.getString("@language");
				alternatives.add(new AlternativeStationName(value, language));
			}
		});
		return alternatives;
	}
}
