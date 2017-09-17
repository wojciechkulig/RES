package org.kulig.renewableenergy.Energy;

import java.util.ArrayList;
import java.util.List;

import org.kulig.renewableenergy.model.entities.PvSystemEnergyBilance;
import org.kulig.renewableenergy.model.entities.Weather;

public class EnergyBilanceCalculator {
	private List<Double> hourlyEnergyConsumption;
	private List<Double> hourlyEnergyYield;
	private List<Weather> hourlyWeatherConditions;
	private int numberOfSettlementPeriods;
	private double periodEnergyGridFeedInDay = 0;
	private double periodEnergyGridFeedInNight = 0;
	private double periodEnergyTakenFromGridDay = 0;
	private double periodEnergyTakenFromGridNight = 0;
	private double periodEnergyConsumptionDay = 0;
	private double periodEnergyConsumptionNight = 0;
	private double periodEnergyAutoConsumptionDay = 0;
	private double periodEnergyAutoConsumptionNight = 0;
	private double periodEnergyYieldDay = 0;
	private double periodEnergyYieldNight = 0;

	public EnergyBilanceCalculator(AnnualEnergyBilanceInputData data) {
		this.hourlyEnergyConsumption = data.getHourlyEnergyConsumption();
		this.hourlyEnergyYield = data.getHourlyEnergyYield();
		this.numberOfSettlementPeriods = data.getNumberOfSettlementPeriods();
		this.hourlyWeatherConditions = data.getHourlyWeatherConditions();
	}

	public List<PvSystemEnergyBilance> calculateYearlyEnergyBilance(String tariffGroup) {
		List<PvSystemEnergyBilance> energyDistribution = new ArrayList<>();
		for (int settlementPeriod = 0; settlementPeriod < numberOfSettlementPeriods; settlementPeriod++) {
			calculateSettlementPeriodEnergyBilance(settlementPeriod,tariffGroup);
			energyDistribution.add(getPeriodEnergyBilance());
			resetPeriod();
		}
		return energyDistribution;
	}

	private void calculateSettlementPeriodEnergyBilance(int settlementPeriod, String tariffGroup) {
		int startMonth = (12 / numberOfSettlementPeriods) * settlementPeriod + 1;
		int endMonth = (12 / numberOfSettlementPeriods) * (settlementPeriod + 1);
		DayNightTariffHoursRecognizer dayNightTariffRecognizer = new DayNightTariffHoursRecognizer(hourlyWeatherConditions, tariffGroup);
		calculateDayTariffEnergyBalance(dayNightTariffRecognizer.getDayTariffHoursForPeriod(startMonth, endMonth));
		calculateNightTariffEnergyBalance(
				dayNightTariffRecognizer.getNightTariffHoursForPeriod(startMonth, endMonth));
	}

	private void calculateNightTariffEnergyBalance(List<Integer> nightTariffHours) {
		for (int hour : nightTariffHours) {
			double energyProduced = hourlyEnergyYield.get(hour);
			double energyConsumption = hourlyEnergyConsumption.get(hour);
			periodEnergyConsumptionNight += energyConsumption;
			periodEnergyYieldNight += energyProduced;
			if (energyConsumption >= energyProduced) {
				periodEnergyAutoConsumptionNight += energyProduced;
				double energyMissing = energyConsumption - energyProduced;
				periodEnergyTakenFromGridNight += energyMissing;
			} else {
				periodEnergyAutoConsumptionNight += energyConsumption;
				double energyExcess = energyProduced - energyConsumption;
				periodEnergyGridFeedInNight += energyExcess;
			}
		}

	}

	private void calculateDayTariffEnergyBalance(List<Integer> dayTariffHours) {
		for (int hour : dayTariffHours) {
			double energyProduced = hourlyEnergyYield.get(hour);
			double energyConsumption = hourlyEnergyConsumption.get(hour);
			periodEnergyConsumptionDay += energyConsumption;
			periodEnergyYieldDay += energyProduced;
			if (energyConsumption >= energyProduced) {
				periodEnergyAutoConsumptionDay += energyProduced;
				double energyMissing = energyConsumption - energyProduced;
				periodEnergyTakenFromGridDay += energyMissing;
			} else {
				periodEnergyAutoConsumptionDay += energyConsumption;
				double energyExcess = energyProduced - energyConsumption;
				periodEnergyGridFeedInDay += energyExcess;
			}
		}

	}

	private void resetPeriod() {
		periodEnergyGridFeedInDay = 0;
		periodEnergyGridFeedInNight = 0;
		periodEnergyTakenFromGridDay = 0;
		periodEnergyTakenFromGridNight = 0;
		periodEnergyConsumptionDay = 0;
		periodEnergyConsumptionNight = 0;
		periodEnergyAutoConsumptionDay = 0;
		periodEnergyAutoConsumptionNight = 0;
		periodEnergyYieldDay = 0;
		periodEnergyYieldNight = 0;

	}

	private PvSystemEnergyBilance getPeriodEnergyBilance() {
		PvSystemEnergyBilance distribution = new PvSystemEnergyBilance();
		distribution.setPeriodEnergyAutoConsumptionDay(periodEnergyAutoConsumptionDay);
		distribution.setPeriodEnergyAutoConsumptionNight(periodEnergyAutoConsumptionNight);
		distribution.setPeriodEnergyConsumptionDay(periodEnergyConsumptionDay);
		distribution.setPeriodEnergyConsumptionNight(periodEnergyConsumptionNight);
		distribution.setPeriodEnergyTakenFromGridDay(periodEnergyTakenFromGridDay);
		distribution.setPeriodEnergyTakenFromGridNight(periodEnergyTakenFromGridNight);
		distribution.setPeriodEnergyYieldDay(periodEnergyYieldDay);
		distribution.setPeriodEnergyYieldNight(periodEnergyYieldNight);
		distribution.setPeriodEnergyGridFeedInDay(periodEnergyGridFeedInDay);
		distribution.setPeriodEnergyGridFeedInNight(periodEnergyGridFeedInNight);
		return distribution;
	}

}
