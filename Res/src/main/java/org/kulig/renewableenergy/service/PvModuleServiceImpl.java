package org.kulig.renewableenergy.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.repository.PvModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public class PvModuleServiceImpl implements PvModuleService {
	@Autowired
	private PvModuleRepository pvModuleRepository;
	@Override
	public Collection<String> getPvModulesNames() throws DataAccessException {
		Collection<PvModule> pvModules = pvModuleRepository.findAll();
		Collection<String> pvModulesNames = pvModules.stream().map(PvModule::getName).collect(Collectors.toList());
		return pvModulesNames;
	}

	@Override
	public Collection<PvModule> getPvModules() throws DataAccessException {
		Collection<PvModule> pvModules = pvModuleRepository.findAll();
		return pvModules;
	}

	@Override
	public PvModule findPvModuleById(int id) throws DataAccessException {
		PvModule pvModule = pvModuleRepository.findById(id);
		return pvModule;
	}

}
