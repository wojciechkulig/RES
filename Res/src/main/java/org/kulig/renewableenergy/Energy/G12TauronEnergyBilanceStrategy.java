package org.kulig.renewableenergy.Energy;

import java.util.List;

import org.kulig.renewableenergy.model.entities.PvSystemEnergyDistribution;

public class G12TauronEnergyBilanceStrategy implements AnnualEnergyBilanceStrategy {
	private double energyStored = 0;
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
	
	private void settlePeriod(PvSystemEnergyDistribution distribution) {
		double energyConsumptionTotal = distribution.getPeriodEnergyTakenFromGridDay() + distribution.getPeriodEnergyTakenFromGridNight();
		double energyGridFeedInTotalTimesCorrection = (distribution.getPeriodEnergyGridFeedInDay() + distribution.getPeriodEnergyGridFeedInNight())*correctionFactor;
		if(energyGridFeedInTotalTimesCorrection >=energyConsumptionTotal){
			distribution.setPeriodEnergyPurchasedDay(0);
			distribution.setPeriodEnergyPurchasedNight(0);
			energyStored = energyGridFeedInTotalTimesCorrection - energyConsumptionTotal;
		}else{
			if(energyGridFeedInTotalTimesCorrection + energyStored >=energyConsumptionTotal){
				energyStored = energyConsumptionTotal -energyGridFeedInTotalTimesCorrection;
				distribution.setPeriodEnergyPurchasedDay(0);
				distribution.setPeriodEnergyPurchasedNight(0);
			}else{
				double energyDiscount = energyStored + energyGridFeedInTotalTimesCorrection;
				energyStored = 0;
				double dayPercentageConsumption = distribution.getPeriodEnergyTakenFromGridDay()/(distribution.getPeriodEnergyTakenFromGridDay()+distribution.getPeriodEnergyGridFeedInNight());
				double nightPercentageConsumption = distribution.getPeriodEnergyGridFeedInNight()/(distribution.getPeriodEnergyTakenFromGridDay()+distribution.getPeriodEnergyGridFeedInNight()); 
				double dayDiscount = energyDiscount * dayPercentageConsumption;
				double nightDiscount = energyDiscount * nightPercentageConsumption;
				distribution.setPeriodEnergyPurchasedDay(distribution.getPeriodEnergyTakenFromGridDay() - dayDiscount);
				distribution.setPeriodEnergyPurchasedNight(distribution.getPeriodEnergyGridFeedInNight() - nightDiscount);
			}
		}
	}


}
