package org.kulig.renewableenergy.model.DTO;

public class LocationDTO {
	
	private double lat;
	private double lng;
	private String azimuth;
	private double inclination;
	private double instalationHeight;
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getAzimuth() {
		return azimuth;
	}
	public void setAzimuth(String azimuth) {
		this.azimuth = azimuth;
	}
	public double getInclination() {
		return inclination;
	}
	public void setInclination(double inclination) {
		this.inclination = inclination;
	}
	public double getInstalationHeight() {
		return instalationHeight;
	}
	public void setInstalationHeight(double instalationHeight) {
		this.instalationHeight = instalationHeight;
	}
	

	
	

}
