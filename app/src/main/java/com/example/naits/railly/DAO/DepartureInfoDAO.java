package com.example.naits.railly.DAO;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.naits.railly.model.DepartureInfo;
import com.example.naits.railly.model.PlatformInfo;

public class DepartureInfoDAO {
	public static DepartureInfo getDepartureInfo(JSONObject obj) throws JSONException {
		int delay = obj.getInt("delay");
		long timeStamp = obj.getLong("time");
		PlatformInfo platform = PlatformDAO.getPlatformInfoWithJSON(obj.getJSONObject("platforminfo"));
		boolean canceled = obj.getString("canceled").equals("1");
		return new DepartureInfo(timeStamp, platform, delay, canceled);
	}
}
