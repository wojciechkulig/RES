package org.kulig.renewableenergy.Energy;

import java.util.List;

import org.kulig.renewableenergy.model.entities.PvSystemEnergyDistribution;

public class G12EnergaEnergyBilanceStrategy implements AnnualEnergyBilanceStrategy {
	private double energyStoredDay = 0;
	private double energyStoredNight = 0;
	private double correctionFactor;

	@Override
	public List<PvSystemEnergyDistribution> calculateAnnualEnergyBalance(AnnualEnergyBilanceInputData data) {
		correctionFactor = data.getCorrectionFactor();
		return settlePeriods(new EnergyBilanceCalculator(data).calculateYearlyEnergyBilance("G12"));
	}

	private List<PvSystemEnergyDistribution> settlePeriods(
			List<PvSystemEnergyDistribution> periodsEnergyBilance) {
		for(PvSystemEnergyDistribution distribution : periodsEnergyBilance){
			settlePeriod(distribution);
		}
		return periodsEnergyBilance;
	}
	//czy bilansowac dzien noca? to jest raczej nie mozliwy przypadek
	private void settlePeriod(PvSystemEnergyDistribution distribution) {
		double energyToPurchaseDay = getAmountOfEnergyPurchasedDay(distribution);
		double energyToPurchaseNight = getAmountOfEnergyPurchasedNight(distribution);
		if(energyToPurchaseNight > 0){
			if(energyStoredDay >= energyToPurchaseNight){
				energyStoredDay -=energyToPurchaseNight;
				energyToPurchaseNight = 0;
			}else{
				energyToPurchaseNight -=energyStoredDay;
				energyStoredDay = 0;
			}
		}
		distribution.setPeriodEnergyPurchasedDay(energyToPurchaseDay);
		distribution.setPeriodEnergyPurchasedNight(energyToPurchaseNight);
		
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
