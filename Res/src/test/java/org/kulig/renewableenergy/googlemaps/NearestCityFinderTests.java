package org.kulig.renewableenergy.googlemaps;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kulig.renewableenergy.model.entities.City;

public class NearestCityFinderTests {
	private static NearestCityFinder finder;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		List<City> cities = new LinkedList<>();
		City krakow = new City();
		krakow.setCityName("Krakow");
		krakow.setLatitude(2);
		krakow.setLongitude(3);
		cities.add(krakow);
		City warszawa = new City();
		warszawa.setCityName("Warszawa");
		warszawa.setLatitude(4);
		warszawa.setLongitude(4);
		cities.add(warszawa);
		
		finder = new NearestCityFinder(cities);
	}

	@Test
	public void testFindNearestCity() {
		City fromCity = new City();
		fromCity.setLatitude(1);
		fromCity.setLongitude(2);
		City nearestCity = finder.findNearestCity(fromCity);		
		assertEquals("Krakow",nearestCity.getCityName());
	}

}
