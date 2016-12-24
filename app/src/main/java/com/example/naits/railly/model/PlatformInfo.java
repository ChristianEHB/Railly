package com.example.naits.railly.model;

public class PlatformInfo {
	private String name, normal;

	public PlatformInfo(String name, String normal) {
		super();
		this.name = name;
		this.normal = normal;
	}

	public String getName() {
		return name;
	}

	public String getNormal() {
		return normal;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNormal(String normal) {
		this.normal = normal;
	}
	
	@Override
	public String toString() {
		return "Platform: " + name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((normal == null) ? 0 : normal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		PlatformInfo other = (PlatformInfo) obj;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		if (normal == null) {
			if (other.normal != null) return false;
		} else if (!normal.equals(other.normal)) return false;
		return true;
	}
}
