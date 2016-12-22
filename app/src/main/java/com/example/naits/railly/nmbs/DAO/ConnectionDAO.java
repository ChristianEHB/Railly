package com.example.naits.railly.nmbs.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONObject;

import com.school.project.nmbs.model.Connection;
import com.school.project.nmbs.model.Departure;
import com.school.project.nmbs.model.Station;
import com.school.project.util.NetUtil;

public class ConnectionDAO {
	private static final String CONNECTIONS_URL = "https://api.irail.be/connections/?to=";

	public static List<Connection> getConnections(String from, String to) throws Exception {
		String finalUrl = CONNECTIONS_URL + to + "&from=" + from + "&format=json";
		String curlUrl = NetUtil.curlURL(finalUrl);
		return getConnections(curlUrl);
	}

	public static List<Connection> getConnections(Station from, Station to) throws Exception {
		return getConnections(from.getFormattedID(), to.getFormattedID());
	}

	public static List<Connection> getConnections(String from, String to, String date, String time, String timeSel)
			throws Exception {
		String finalUrl = CONNECTIONS_URL + to + "&from=" + from + "&format=json" + "&date=" + date + "&time=" + time + "&timeSel=" + timeSel ;
		String curlUrl = NetUtil.curlURL(finalUrl);
		return getConnections(curlUrl);
	}

	private static List<Connection> getConnections(String url) throws Exception {
		List<Connection> connections = new ArrayList<Connection>();
		try {
			JSONObject jBase = new JSONObject(url);
			if (jBase.has("error")) {
				throw new Exception(jBase.getString("message"));
			}

			JSONArray arrCon = jBase.getJSONArray("connection");
			arrCon.forEach(new Consumer<Object>() {
				@Override
				public void accept(Object t) {
					JSONObject obj = (JSONObject) t;
					Connection c = getConnection(obj);
					connections.add(c);
				}
			});
		} catch (IOException io) {
			System.err.println("Error in StationDAO.loadCache()");
			io.printStackTrace();
		}
		return connections;
	}

	private static Connection getConnection(JSONObject obj) {
		int id = obj.getInt("id");
		int duration = obj.getInt("duration");
		Departure departure = DepartureDAO.getDeparture(obj.getJSONObject("departure"));
		Departure arrival = DepartureDAO.getDeparture(obj.getJSONObject("arrival"));
		return new Connection(id, duration, departure, arrival, ViaDAO.getVias(obj));
	}
}
