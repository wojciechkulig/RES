package org.kulig.renewableenergy.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.kulig.renewableenergy.model.entities.TariffGroup;
import org.kulig.renewableenergy.repository.TariffGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public class TariffGroupServiceImpl implements TariffGroupService {
	@Autowired
	private TariffGroupRepository tariffGroupRepository;

	@Override
	public Collection<String> getTariffGroupNames() throws DataAccessException {
		Collection<TariffGroup> tariffGroups =  tariffGroupRepository.findAllOrderByTariffGroupName();
		Collection<String> names = tariffGroups.stream().map(TariffGroup::getName).collect(Collectors.toList());
		return names;
	}

	@Override
	public Collection<TariffGroup> getTariffGroups() throws DataAccessException {
		Collection<TariffGroup> tariffGroups = tariffGroupRepository.findAllOrderByTariffGroupName();
		return tariffGroups;
	}

	@Override
	public TariffGroup findTariffGroupById(int id) throws DataAccessException {
		TariffGroup tariffGroup = tariffGroupRepository.findById(id);
		return tariffGroup;
	}

}
