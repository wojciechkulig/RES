package org.kulig.renewableenergy.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.kulig.renewableenergy.model.entities.Inverter;
import org.kulig.renewableenergy.repository.InverterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public class InverterServiceImpl implements InverterService {

	@Autowired
	private InverterRepository inverterRepository;
	@Override
	public Collection<String> getInvertersNames() throws DataAccessException {
		Collection<Inverter> inverters = inverterRepository.findAll();
		Collection<String> invertersNames = inverters.stream().map(Inverter::getName).collect(Collectors.toList());
		return invertersNames;
	}

	@Override
	public Collection<Inverter> getInverters() throws DataAccessException {
		Collection<Inverter> inverters = inverterRepository.findAll();
		return inverters;
	}

	@Override
	public Inverter findInverterById(int id) throws DataAccessException {
		Inverter inverter = inverterRepository.findById(id);
		return inverter;
	}

}
