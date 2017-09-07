package org.kulig.renewableenergy.service;

import java.util.Collection;
import java.util.List;

import org.kulig.renewableenergy.model.DTO.LocationDTO;
import org.kulig.renewableenergy.model.entities.City;
import org.springframework.dao.DataAccessException;

public interface CityService {
	
	Collection<String> getCityNames() throws DataAccessException;
	Collection<City> getCities() throws DataAccessException;
	City findCityById(int id) throws DataAccessException;
	City setCityAndWeatherConditionsForLocation(LocationDTO locationDTO);
	List<Double> getCityMonthlyIrradiance(City city);
	

}
