package com.example.naits.railly.DAO;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.naits.railly.model.Vehicle;

public class VehicleDAO {

	public static Vehicle getVehicleFromJSONObject(JSONObject obj) throws JSONException {
		String id = obj.getString("@id");
		String formattedID = obj.getString("name");
		return new Vehicle(id, formattedID);
	}

}
