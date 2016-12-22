package com.example.naits.railly.nmbs.model;

public class Vehicle {
	private String id, formattedID;

	public Vehicle(String id, String formattedID) {
		super();
		this.id = id;
		this.formattedID = formattedID;
	}
	
	public String getId() {
		return id;
	}

	public String getFormattedID() {
		return formattedID;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFormattedID(String formattedID) {
		this.formattedID = formattedID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formattedID == null) ? 0 : formattedID.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Vehicle other = (Vehicle) obj;
		if (formattedID == null) {
			if (other.formattedID != null) return false;
		} else if (!formattedID.equals(other.formattedID)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Vehicle: " + formattedID;
	}
}
