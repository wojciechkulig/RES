package org.kulig.renewableenergy.repository.springdatajpa;

import java.util.Collection;

import org.kulig.renewableenergy.model.entities.Inverter;
import org.kulig.renewableenergy.repository.InverterRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataInverterRepository extends InverterRepository, CrudRepository<Inverter,Integer> {
	@Override
	Inverter findById(int id) throws DataAccessException;
	
	@Override
	Collection<Inverter> findAll() throws DataAccessException;
	
	@SuppressWarnings("unchecked")
	@Override
	Inverter save(Inverter inverter) throws DataAccessException;
}
