package org.kulig.renewableenergy.service;

import java.util.Collection;

import org.kulig.renewableenergy.model.entities.TariffGroup;
import org.springframework.dao.DataAccessException;

public interface TariffGroupService {
	Collection<String> getTariffGroupNames() throws DataAccessException;
	Collection<TariffGroup> getTariffGroups() throws DataAccessException;
	TariffGroup findTariffGroupById(int id) throws DataAccessException;
}
