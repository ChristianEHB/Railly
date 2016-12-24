package com.example.naits.railly.DAO;

import java.util.ArrayList;
import java.util.List;
//import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.naits.railly.model.*;

public class ViaDAO {

	public static List<Via> getVias(JSONObject obj) throws JSONException {
		List<Via> vias = new ArrayList<Via>();
		if(obj.has("vias")) {
			JSONObject objVias = obj.getJSONObject("vias");

			JSONArray arrVia = objVias.getJSONArray("via");
			/*objVias.getJSONArray("via").forEach(new Consumer<Object>() {
				@Override
				public void accept(Object t) {
					JSONObject obj = (JSONObject)t;
					vias.add(getVia(obj));
				}
			});*/

			for(int i = 0; i < arrVia.length(); i++){
				JSONObject viaObj = (JSONObject) arrVia.get(i);
				Via v = getVia(viaObj);
				vias.add(v);
			}
		}
		return vias;
	}
	
	public static Via getVia(JSONObject obj) throws JSONException {
		int id = obj.getInt("id");
		JSONObject stationObj = obj.getJSONObject("stationinfo");
		Station station = StationCache.getInstance().getStationWithID(stationObj.getString("@id"));
		Vehicle vehicle = new Vehicle("NOT_USED", obj.getString("vehicle"));
		String direction = obj.getJSONObject("direction").getString("name");
		DepartureInfo arrInfo = DepartureInfoDAO.getDepartureInfo(obj.getJSONObject("arrival"));
		DepartureInfo depInfo = DepartureInfoDAO.getDepartureInfo(obj.getJSONObject("departure"));
		return new Via(id, arrInfo, depInfo, station, vehicle, direction);
	}
}
