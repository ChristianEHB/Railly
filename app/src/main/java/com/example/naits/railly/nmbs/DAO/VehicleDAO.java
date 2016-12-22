package com.example.naits.railly.nmbs.DAO;

import org.json.JSONObject;

import com.school.project.nmbs.model.Vehicle;

public class VehicleDAO {

	public static Vehicle getVehicleFromJSONObject(JSONObject obj) {
		String id = obj.getString("@id");
		String formattedID = obj.getString("name");
		return new Vehicle(id, formattedID);
	}

}
