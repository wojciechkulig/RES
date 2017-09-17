package org.kulig.renewableenergy.service;

import java.util.Collection;
import java.util.List;

import org.kulig.renewableenergy.model.DTO.PvSystemConfigurationDTO;
import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.PvSystemEnergyBilance;
import org.kulig.renewableenergy.model.entities.TariffGroup;

public interface PvSystemConfigurationService {
	public Collection<TariffGroup> getTariffGroups();
	public Collection<PvModule> getPvModules();
	List<List<PvSystemEnergyBilance>> calculateLifeTimeEnergyBilance(PvSystemConfigurationDTO systemConfigurationData);
	public void updateEnergyBilance(List<List<PvSystemEnergyBilance>> bilance);
	public String createEnergyBilanceBarChart(List<List<PvSystemEnergyBilance>> bilance);
	void updatePvModuleBean(PvModule module);
	void updateTariffGroupBean(TariffGroup tariffGroup, double annualEnergyConsumption);
}
