package org.kulig.renewableenergy.googlemaps;

import java.util.Collection;

import org.kulig.renewableenergy.model.entities.City;

public class NearestCityFinder {
	private final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
	private Collection<City> cities;

	public NearestCityFinder(Collection<City> cities) {
		this.cities = cities;
	}

	public City findNearestCity(City cityFrom) {
		City nearestCity = null;
		double shortestDistance = AVERAGE_RADIUS_OF_EARTH_KM;
		for(City cityTo: cities){
			double distanceBetweenCities = calculateDistanceBetweenCities(cityFrom,cityTo);			
			if (distanceBetweenCities<shortestDistance){
				shortestDistance = distanceBetweenCities;
				nearestCity = cityTo;
			}
		}
		return nearestCity;
		
	}
	//calculate distance between two locations using Haversine formula	 
	public double calculateDistanceBetweenCities(City Cityfrom, City CityTo) {
		double fromLat = Cityfrom.getLatitude();
		double fromLng = Cityfrom.getLongitude();
		double toLat = CityTo.getLatitude();
		double toLng = CityTo.getLongitude();
		

	    double latDistance = Math.toRadians(fromLat - toLat);
	    double lngDistance = Math.toRadians(fromLng - toLng);

	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	      + Math.cos(Math.toRadians(fromLat)) * Math.cos(Math.toRadians(toLat))
	      * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	    return AVERAGE_RADIUS_OF_EARTH_KM * c;
	}

}
