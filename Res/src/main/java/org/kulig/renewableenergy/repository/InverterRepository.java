package org.kulig.renewableenergy.repository;

import java.util.Collection;

import org.kulig.renewableenergy.model.entities.Inverter;
import org.springframework.dao.DataAccessException;

public interface InverterRepository {
	Inverter findById(int id) throws DataAccessException;
	Collection<Inverter> findAll() throws DataAccessException;
	Inverter save(Inverter inverter) throws DataAccessException;
}
