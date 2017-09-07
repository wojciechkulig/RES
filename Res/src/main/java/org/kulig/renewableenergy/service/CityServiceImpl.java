package org.kulig.renewableenergy.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.kulig.renewableenergy.googlemaps.NearestCityFinder;
import org.kulig.renewableenergy.model.DTO.LocationDTO;
import org.kulig.renewableenergy.model.entities.City;
import org.kulig.renewableenergy.model.entities.Weather;
import org.kulig.renewableenergy.repository.CityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ApplicationContext context;

	@Override
	public Collection<String> getCityNames() throws DataAccessException {
		Collection<City> cities =  cityRepository.findAllOrderByCityName();
		Collection<String> names = cities.stream().map(City::getCityName).collect(Collectors.toList());
		return names;
	}

	@Override
	public Collection<City> getCities() throws DataAccessException {
		Collection<City> cities = cityRepository.findAllOrderByCityName();
		return cities;
	}

	@Override
	public City findCityById(int id) throws DataAccessException {
		City city = cityRepository.findById(id);
		return city;
	}
	
	@Override
	public City setCityAndWeatherConditionsForLocation(LocationDTO locationDTO) {		
		City nearestCity = getNearestCity(locationDTO);
		City city = context.getBean(City.class);
		BeanUtils.copyProperties(nearestCity, city);
		correctWeatherConditionsForGivenInstallationLocation(city.getWeather(), locationDTO);
		return city;
	}
	private City getNearestCity(LocationDTO locationDTO){
		City cityFrom = new City();
		cityFrom.setLatitude(locationDTO.getLat());
		cityFrom.setLongitude(locationDTO.getLng());
		return new NearestCityFinder(getCities()).findNearestCity(cityFrom);
	}
	private void correctWeatherConditionsForGivenInstallationLocation(List<Weather> AnnualWeather, LocationDTO locationDTO){
		for(Weather weather : AnnualWeather){
			weather.setIrradiance(locationDTO.getAzimuth(),locationDTO.getInclination());
			weather.setWindSpeedOnGivenHeight(locationDTO.getInstalationHeight());
		}
	}
	public List<Double> getCityMonthlyIrradiance(City city){
		List<Weather> weather = city.getWeather();
		return new LinkedList<Double>(weather.stream().collect(Collectors.groupingBy(Weather::getM, Collectors.summingDouble(Weather::getIrradiance))).values());
	}

}
