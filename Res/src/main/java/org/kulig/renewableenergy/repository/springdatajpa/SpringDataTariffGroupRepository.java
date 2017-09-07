package org.kulig.renewableenergy.repository.springdatajpa;

import java.util.Collection;

import org.kulig.renewableenergy.model.entities.TariffGroup;
import org.kulig.renewableenergy.repository.TariffGroupRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataTariffGroupRepository extends TariffGroupRepository, CrudRepository<TariffGroup,Integer> {
	
	TariffGroup findById(int id) throws DataAccessException;
	
	@Query("Select p from TariffGroup p order By p.name")
	Collection<TariffGroup> findAllOrderByTariffGroupName() throws DataAccessException;

}
