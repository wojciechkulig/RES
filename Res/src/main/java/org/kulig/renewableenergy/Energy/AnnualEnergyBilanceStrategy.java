package org.kulig.renewableenergy.Energy;

import java.util.List;

import org.kulig.renewableenergy.model.entities.PvSystemEnergyBilance;

public interface AnnualEnergyBilanceStrategy {
	public List<PvSystemEnergyBilance> calculateAnnualEnergyBalance(AnnualEnergyBilanceInputData data);

}
