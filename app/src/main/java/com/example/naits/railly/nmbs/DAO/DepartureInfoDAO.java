package com.example.naits.railly.nmbs.DAO;

import org.json.JSONObject;

import com.school.project.nmbs.model.DepartureInfo;
import com.school.project.nmbs.model.PlatformInfo;

public class DepartureInfoDAO {
	public static DepartureInfo getDepartureInfo(JSONObject obj) {
		int delay = obj.getInt("delay");
		long timeStamp = obj.getLong("time");
		PlatformInfo platform = PlatformDAO.getPlatformInfoWithJSON(obj.getJSONObject("platforminfo"));
		boolean canceled = obj.getString("canceled").equals("1");
		return new DepartureInfo(timeStamp, platform, delay, canceled);
	}
}
