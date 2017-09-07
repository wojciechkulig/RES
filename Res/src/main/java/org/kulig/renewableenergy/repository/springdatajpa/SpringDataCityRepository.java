package org.kulig.renewableenergy.repository.springdatajpa;

import java.util.List;

import org.kulig.renewableenergy.model.entities.City;
import org.kulig.renewableenergy.repository.CityRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface SpringDataCityRepository extends CityRepository, CrudRepository<City,Integer>{
	@Override
	City findById(int it) throws DataAccessException;
	
	@Override
	@Query("Select p from City p order By p.cityName")
	List<City> findAllOrderByCityName() throws DataAccessException;
}
