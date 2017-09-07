package org.kulig.renewableenergy.service;

import java.util.Collection;

import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.TariffGroup;

public interface PvSystemConfigurationService {
	public Collection<TariffGroup> getTariffGroups();
	public Collection<PvModule> getPvModules();
	void calculate();
	void updatePvModuleBean(PvModule module);
	void updateTariffGroupBean(TariffGroup tariffGroup, double annualEnergyConsumption);
}
