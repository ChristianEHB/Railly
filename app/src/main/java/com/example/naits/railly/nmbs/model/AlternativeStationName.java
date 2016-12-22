package com.example.naits.railly.nmbs.model;

public class AlternativeStationName {
private String value, language;
	
	public AlternativeStationName(String value, String language) {
		this.value = value;
		this.language = language;
	}

	public String getValue() {
		return value;
	}

	public String getLanguage() {
		return language;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		AlternativeStationName other = (AlternativeStationName) obj;
		if (language == null) {
			if (other.language != null) return false;
		} else if (!language.equals(other.language)) return false;
		if (value == null) {
			if (other.value != null) return false;
		} else if (!value.equals(other.value)) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Alternative station name: " + value + "(" + language + ")";
	}
}
