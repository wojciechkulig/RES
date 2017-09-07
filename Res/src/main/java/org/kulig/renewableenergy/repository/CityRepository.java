package org.kulig.renewableenergy.repository;

import java.util.Collection;

import org.kulig.renewableenergy.model.entities.City;
import org.springframework.dao.DataAccessException;

public interface CityRepository {
	
	City findById(int it) throws DataAccessException;
	
	Collection<City> findAllOrderByCityName() throws DataAccessException;
}
