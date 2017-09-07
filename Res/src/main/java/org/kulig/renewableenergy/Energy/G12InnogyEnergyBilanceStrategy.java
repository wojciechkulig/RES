package org.kulig.renewableenergy.Energy;

import java.util.List;

import org.kulig.renewableenergy.model.entities.PvSystemEnergyDistribution;

public class G12InnogyEnergyBilanceStrategy implements AnnualEnergyBilanceStrategy {
	private double energyStoredDay = 0;
	private double energyStoredNight = 0;
	private double correctionFactor;

	@Override
	public List<PvSystemEnergyDistribution> calculateAnnualEnergyBalance(AnnualEnergyBilanceInputData data) {
		correctionFactor = data.getCorrectionFactor();
		return settlePeriods(new EnergyBilanceCalculator(data).calculateYearlyEnergyBilance("G12"));
	}

	private List<PvSystemEnergyDistribution> settlePeriods(List<PvSystemEnergyDistribution> periodsEnergyBilance) {
		for (PvSystemEnergyDistribution distribution : periodsEnergyBilance) {
			distribution.setPeriodEnergyPurchasedDay(getAmountOfEnergyPurchasedDay(distribution));
			distribution.setPeriodEnergyPurchasedNight(getAmountOfEnergyPurchasedNight(distribution));
		}
		return null;
	}

	private double getAmountOfEnergyPurchasedDay(PvSystemEnergyDistribution distribution) {
		double energyToPurchase = distribution.getPeriodEnergyTakenFromGridDay()
				- distribution.getPeriodEnergyGridFeedInDay() * correctionFactor;
		if (energyToPurchase >= 0) {
			return getAmountOfEnergyPurchasedMinusEnergyStoredDay(energyToPurchase);
		}
		storeExcessEnergyDay(energyToPurchase);
		return 0;
	}

	private double getAmountOfEnergyPurchasedMinusEnergyStoredDay(double energyToPurchase) {
		if (energyStoredDay >= energyToPurchase) {
			energyStoredDay -= energyToPurchase;
			return 0;
		}
		energyStoredDay = 0;
		return energyToPurchase - energyStoredDay;
	}

	private void storeExcessEnergyDay(double energyToPurchase) {
		energyStoredDay += energyToPurchase;
	}

	private double getAmountOfEnergyPurchasedNight(PvSystemEnergyDistribution distribution) {
		double energyToPurchase = distribution.getPeriodEnergyTakenFromGridNight()
				- distribution.getPeriodEnergyGridFeedInNight() * correctionFactor;
		if (energyToPurchase >= 0) {
			return getAmountOfEnergyPurchasedMinusEnergyStoredNight(energyToPurchase);
		}
		storeExcessEnergyNight(energyToPurchase);
		return 0;
	}

	private double getAmountOfEnergyPurchasedMinusEnergyStoredNight(double energyToPurchase) {
		if (energyStoredNight >= energyToPurchase) {
			energyStoredNight -= energyToPurchase;
			return 0;
		}
		energyStoredNight = 0;
		return energyToPurchase - energyStoredNight;
	}

	private void storeExcessEnergyNight(double energyToPurchase) {
		energyStoredNight += energyToPurchase;
	}

}
