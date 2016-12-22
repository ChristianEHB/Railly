package com.example.naits.railly.nmbs.model;

import com.example.naits.railly.util.DateUtil;

public class Departure {
	private Station toStation;
	private Vehicle vehicle;
	private DepartureInfo depInfo;
	
	public Departure(Station toStation, Vehicle vehicle, DepartureInfo depInfo) {
		this.toStation = toStation;
		this.vehicle = vehicle;
		this.depInfo = depInfo;
	}

	public Station getToStation() {
		return toStation;
	}

	public DepartureInfo getDepartureInfo() {
		return depInfo;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setToStation(Station toStation) {
		this.toStation = toStation;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getDirection() {
		return toStation.getName();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Departure: to " + toStation.getName() + "\n");
		sb.append("\t at " + DateUtil.timeStampToDate(depInfo.getTimeStamp()) + " on " + depInfo.getPlatform().toString());
		return sb.toString();
	}
}
