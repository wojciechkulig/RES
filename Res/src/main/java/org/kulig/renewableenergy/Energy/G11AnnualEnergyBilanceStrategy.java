package org.kulig.renewableenergy.Energy;

import java.util.List;

import org.kulig.renewableenergy.model.entities.PvSystemEnergyDistribution;

public class G11AnnualEnergyBilanceStrategy implements AnnualEnergyBilanceStrategy {
	private double energyStored = 0;
	private double correctionFactor;

	@Override
	public List<PvSystemEnergyDistribution> calculateAnnualEnergyBalance(AnnualEnergyBilanceInputData data) {
		correctionFactor = data.getCorrectionFactor();
		return settlePeriods(new EnergyBilanceCalculator(data).calculateYearlyEnergyBilance("G11"));
	}

	private List<PvSystemEnergyDistribution> settlePeriods(List<PvSystemEnergyDistribution> periodsEnergyBilance) {
		for (PvSystemEnergyDistribution distribution : periodsEnergyBilance) {
			distribution.setPeriodEnergyPurchasedDay(getAmountOfEnergyPurchased(distribution));
		}
		return periodsEnergyBilance;
	}

	private double getAmountOfEnergyPurchased(PvSystemEnergyDistribution distribution) {
		double energyToPurchase = distribution.getPeriodEnergyTakenFromGridDay()
				- distribution.getPeriodEnergyGridFeedInDay() * correctionFactor;
		if (energyToPurchase >= 0) {
			return getAmountOfEnergyPurchasedMinusEnergyStored(energyToPurchase);
		}
		storeExcessEnergy(energyToPurchase);
		return 0;
	}

	private double getAmountOfEnergyPurchasedMinusEnergyStored(double energyToPurchase) {
		if (energyStored >= energyToPurchase) {
			energyStored -= energyToPurchase;
			return 0;
		}
		energyStored = 0;
		return energyToPurchase - energyStored;
	}

	private void storeExcessEnergy(double energyToPurchase) {
		energyStored += energyToPurchase;
	}

}
