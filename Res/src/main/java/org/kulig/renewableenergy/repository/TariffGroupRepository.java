package org.kulig.renewableenergy.repository;

import java.util.Collection;

import org.kulig.renewableenergy.model.entities.TariffGroup;
import org.springframework.dao.DataAccessException;

public interface TariffGroupRepository {
	
	TariffGroup findById(int id) throws DataAccessException;
	
	Collection<TariffGroup> findAllOrderByTariffGroupName() throws DataAccessException;
}
