package org.kulig.renewableenergy.Energy;

import java.util.List;

import org.kulig.renewableenergy.model.entities.PvSystemEnergyDistribution;

public class G12WzorPoczatkowy {
	private double energyStoredDay = 0;
	private double energyStoredNight = 0;
	private double correctionFactor;

	
	public List<PvSystemEnergyDistribution> calculateAnnualEnergyBalance(AnnualEnergyBilanceInputData data) {
		correctionFactor = data.getCorrectionFactor();
		return settlePeriods(new EnergyBilanceCalculator(data).calculateYearlyEnergyBilance("G12"));
	}

	private List<PvSystemEnergyDistribution> settlePeriods(
			List<PvSystemEnergyDistribution> periodsEnergyBilance) {
		for(PvSystemEnergyDistribution distribution : periodsEnergyBilance){
			distribution.setPeriodEnergyPurchasedDay(getAmountOfEnergyPurchasedDay(distribution));
			distribution.setPeriodEnergyPurchasedNight(periodEnergyPurchasedNight(distribution));
		}
		return null;
	}

	private double periodEnergyPurchasedNight(PvSystemEnergyDistribution distribution) {
		// TODO Auto-generated method stub
		return 0;
	}

	private double getAmountOfEnergyPurchasedDay(PvSystemEnergyDistribution distribution) {
		// TODO Auto-generated method stub
		return 0;
	}
}
