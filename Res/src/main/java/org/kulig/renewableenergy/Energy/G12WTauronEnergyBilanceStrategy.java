package org.kulig.renewableenergy.Energy;

import java.util.List;

import org.kulig.renewableenergy.model.entities.PvSystemEnergyBilance;

public class G12WTauronEnergyBilanceStrategy implements AnnualEnergyBilanceStrategy {

	private double energyStored = 0;
	private double correctionFactor;

	@Override
	public List<PvSystemEnergyBilance> calculateAnnualEnergyBalance(AnnualEnergyBilanceInputData data) {
		correctionFactor = data.getCorrectionFactor();
		return settlePeriods(new EnergyBilanceCalculator(data).calculateYearlyEnergyBilance("G12W"));
	}

	private List<PvSystemEnergyBilance> settlePeriods(
			List<PvSystemEnergyBilance> periodsEnergyBilance) {
		for(PvSystemEnergyBilance distribution : periodsEnergyBilance){
			settlePeriod(distribution);
		}
		return periodsEnergyBilance;
	}
	
	private void settlePeriod(PvSystemEnergyBilance distribution) {
		double energyConsumptionTotal = distribution.getPeriodEnergyTakenFromGridDay() + distribution.getPeriodEnergyTakenFromGridNight();
		double energyGridFeedInTotalTimesCorrection = (distribution.getPeriodEnergyGridFeedInDay() + distribution.getPeriodEnergyGridFeedInNight())*correctionFactor;
		if(energyGridFeedInTotalTimesCorrection >=energyConsumptionTotal){
			distribution.setPeriodEnergyPurchasedDay(0);
			distribution.setPeriodEnergyPurchasedNight(0);
			energyStored += energyGridFeedInTotalTimesCorrection - energyConsumptionTotal;
		}else{
			if(energyGridFeedInTotalTimesCorrection + energyStored >=energyConsumptionTotal){
				energyStored -= energyConsumptionTotal -energyGridFeedInTotalTimesCorrection;
				distribution.setPeriodEnergyPurchasedDay(0);
				distribution.setPeriodEnergyPurchasedNight(0);
			}else{
				double energyDiscount = energyStored + energyGridFeedInTotalTimesCorrection;
				energyStored = 0;
				double dayPercentageConsumption = distribution.getPeriodEnergyTakenFromGridDay()/(distribution.getPeriodEnergyTakenFromGridDay()+distribution.getPeriodEnergyTakenFromGridNight());
				double nightPercentageConsumption = distribution.getPeriodEnergyTakenFromGridNight()/(distribution.getPeriodEnergyTakenFromGridDay()+distribution.getPeriodEnergyTakenFromGridNight()); 
				double dayDiscount = energyDiscount * dayPercentageConsumption;
				double nightDiscount = energyDiscount * nightPercentageConsumption;
				distribution.setPeriodEnergyPurchasedDay(distribution.getPeriodEnergyTakenFromGridDay() - dayDiscount);
				distribution.setPeriodEnergyPurchasedNight(distribution.getPeriodEnergyGridFeedInNight() - nightDiscount);
			}
		}
	}

}
