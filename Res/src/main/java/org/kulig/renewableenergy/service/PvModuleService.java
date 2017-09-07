package org.kulig.renewableenergy.service;

import java.util.Collection;

import org.kulig.renewableenergy.model.entities.PvModule;
import org.springframework.dao.DataAccessException;

public interface PvModuleService {
	Collection<String> getPvModulesNames() throws DataAccessException;
	Collection<PvModule> getPvModules() throws DataAccessException;
	PvModule findPvModuleById(int id) throws DataAccessException;
}
