package com.example.naits.railly.DAO;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.naits.railly.model.PlatformInfo;

public class PlatformDAO {
	public static PlatformInfo getPlatformInfoWithJSON(JSONObject obj) throws JSONException {
		String name = obj.getString("name");
		String normal = obj.getString("normal");
		return new PlatformInfo(name, normal);
	}
}
