package com.example.naits.railly.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.naits.railly.model.*;

public class DepartureDAO {
    private static final String BEGIN_LIVEBOARD_URL = "https://api.irail.be/liveboard/?id=";
    private static final String END_LIVEBOARD_URL = "&format=json";


//    public static List<Departure> getDeparturesFrom(Station fromStation) throws JSONException {
//        List<Departure> departures = new ArrayList<Departure>();
//        try {
//            String finalURl = BEGIN_LIVEBOARD_URL + fromStation.getFormattedID() + END_LIVEBOARD_URL;
//            String curlUrl = NetUtil.curlURL(finalURl);
//
//            JSONObject jBase = new JSONObject(curlUrl);
//            JSONObject jDepartures = jBase.getJSONObject("departures");
//            JSONArray arrDep = jDepartures.getJSONArray("departure");
//            /*arrDep.forEach(new Consumer<Object>() {
//                @Override
//				public void accept(Object t) {
//					JSONObject obj = (JSONObject)t;
//					Departure d = getDeparture(obj);
//					departures.add(d);
//				}
//			});*/
//            for(int i = 0; i < arrDep.length(); i++){
//                JSONObject obj = (JSONObject) arrDep.get(i);
//                Departure d = getDeparture(obj);
//                departures.add(d);
//            }
//
//        } catch (IOException io) {
//            System.err.println("Error in StationDAO.loadCache()");
//            io.printStackTrace();
//        }
//        return departures;
//    }


    public static Departure getDeparture(JSONObject obj) throws JSONException {
        JSONObject station = obj.getJSONObject("stationinfo");
        Station toStation = StationCache.getInstance().getStationWithID(station.getString("@id"));
        Vehicle vehicle = null;
        if (obj.has("vehicleinfo"))
            vehicle = VehicleDAO.getVehicleFromJSONObject(obj.getJSONObject("vehicleinfo"));
        else vehicle = new Vehicle("NOT_USED", obj.getString("vehicle"));
        return new Departure(toStation, vehicle, DepartureInfoDAO.getDepartureInfo(obj));
    }
}
