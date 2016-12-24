package com.example.naits.railly.model;

public class Via {
	private int id;
	private DepartureInfo arrInfo, depInfo;
	private Station station;
	private Vehicle vehicle;
	private String direction;
	
	public Via(int id, DepartureInfo arrInfo, DepartureInfo depInfo, Station station, Vehicle vehicle, String direction) {
		super();
		this.id = id;
		this.arrInfo = arrInfo;
		this.depInfo = depInfo;
		this.station = station;
		this.vehicle = vehicle;
		this.direction = direction;
	}

	public int getId() {
		return id;
	}

	public DepartureInfo getArrInfo() {
		return arrInfo;
	}

	public DepartureInfo getDepInfo() {
		return depInfo;
	}

	public Station getStation() {
		return station;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public String getDirection() {
		return direction;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setArrInfo(DepartureInfo arrInfo) {
		this.arrInfo = arrInfo;
	}

	public void setDepInfo(DepartureInfo depInfo) {
		this.depInfo = depInfo;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrInfo == null) ? 0 : arrInfo.hashCode());
		result = prime * result + ((depInfo == null) ? 0 : depInfo.hashCode());
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + id;
		result = prime * result + ((station == null) ? 0 : station.hashCode());
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Via other = (Via) obj;
		if (arrInfo == null) {
			if (other.arrInfo != null) return false;
		} else if (!arrInfo.equals(other.arrInfo)) return false;
		if (depInfo == null) {
			if (other.depInfo != null) return false;
		} else if (!depInfo.equals(other.depInfo)) return false;
		if (direction == null) {
			if (other.direction != null) return false;
		} else if (!direction.equals(other.direction)) return false;
		if (id != other.id) return false;
		if (station == null) {
			if (other.station != null) return false;
		} else if (!station.equals(other.station)) return false;
		if (vehicle == null) {
			if (other.vehicle != null) return false;
		} else if (!vehicle.equals(other.vehicle)) return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("->Take the train direction " + direction + " stop at " + station.getName() + " on " + arrInfo.toString());
		sb.append("\n\t<-Next train at " + depInfo.toString());
		return sb.toString();
	}
}
