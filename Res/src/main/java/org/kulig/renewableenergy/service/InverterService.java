package org.kulig.renewableenergy.service;

import java.util.Collection;

import org.kulig.renewableenergy.model.entities.Inverter;
import org.springframework.dao.DataAccessException;

public interface InverterService {
	
	Collection<String> getInvertersNames() throws DataAccessException;
	Collection<Inverter> getInverters() throws DataAccessException;
	Inverter findInverterById(int id) throws DataAccessException;

}
