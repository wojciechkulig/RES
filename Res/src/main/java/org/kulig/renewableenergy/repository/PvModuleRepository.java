package org.kulig.renewableenergy.repository;

import java.util.Collection;

import org.kulig.renewableenergy.model.entities.PvModule;
import org.springframework.dao.DataAccessException;

public interface PvModuleRepository {
	PvModule findById(int id) throws DataAccessException;
	Collection<PvModule> findAll() throws DataAccessException;
	PvModule save(PvModule pvModule) throws DataAccessException;
}
