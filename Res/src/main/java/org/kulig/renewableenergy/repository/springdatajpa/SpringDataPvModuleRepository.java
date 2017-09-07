package org.kulig.renewableenergy.repository.springdatajpa;

import java.util.Collection;

import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.repository.PvModuleRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataPvModuleRepository extends PvModuleRepository, CrudRepository<PvModule,Integer> {

	@Override
	PvModule findById(int id) throws DataAccessException;

	@Override
	Collection<PvModule> findAll() throws DataAccessException;

	@SuppressWarnings("unchecked")
	@Override
	PvModule save(PvModule pvModule) throws DataAccessException; 

	
}
