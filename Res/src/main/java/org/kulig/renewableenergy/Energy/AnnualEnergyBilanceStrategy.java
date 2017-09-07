package org.kulig.renewableenergy.Energy;

import java.util.List;

import org.kulig.renewableenergy.model.entities.PvSystemEnergyDistribution;

public interface AnnualEnergyBilanceStrategy {
	public List<PvSystemEnergyDistribution> calculateAnnualEnergyBalance(AnnualEnergyBilanceInputData data);

}
