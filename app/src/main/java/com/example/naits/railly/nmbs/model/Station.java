package com.example.naits.railly.nmbs.model;

import java.util.ArrayList;
import java.util.List;

public class Station {
	private String name;
	private String id;
	private String country;
	private String avgStops;
	private double latitude, longitude;
	private List<AlternativeStationName> alternatives;

	public Station(String name, String id, String country, String avgStops, double lat, double lon) {
		this.name = name;
		this.id = id;
		this.country = country;
		this.avgStops = avgStops;
		latitude = lat;
		longitude = lon;
		alternatives = new ArrayList<AlternativeStationName>();
	}
	
	public void addAlternativeStationName(AlternativeStationName alt) {
		alternatives.add(alt);
	}
	
	public List<AlternativeStationName> getAlternatives() {
		return alternatives;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getCountry() {
		return country;
	}

	public String getAvgStops() {
		return avgStops;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setAvgStops(String avgStops) {
		this.avgStops = avgStops;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public void setAlternativeStationNames(List<AlternativeStationName> alt) {
		alternatives = alt;
	}
	
	/**
	 * @return Return a formatted ID that is used for the NMBS Api
	 * */
	public String getFormattedID() {
		String newID = id.replace("http://irail.be/stations", "BE");
		newID = newID.replaceAll("/", ".");
		return newID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(obj instanceof Station) {
			Station tmp = (Station) obj;
			return tmp.id.equals(id) && tmp.name.equals(name);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Station: " + name + "[" + id + "]";
	}
}
