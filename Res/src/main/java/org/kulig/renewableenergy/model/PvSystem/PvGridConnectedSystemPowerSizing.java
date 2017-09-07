package org.kulig.renewableenergy.model.PvSystem;

import java.util.List;

import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.Weather;

public class PvGridConnectedSystemPowerSizing {
	private static final double DC_TO_AC_POWER_LOSSESS_COEFFICIENT = 1.15;
	private static final int NUMBER_OF_DAYS_PER_ONE_YEAR = 365;
	private List<Weather> meteorologicalYear;
	private double annualEnergyConsumption;

	public PvGridConnectedSystemPowerSizing(List<Weather> meteorologicalYear, double annualEnergyConsumption) {
		this.meteorologicalYear = meteorologicalYear;
		this.annualEnergyConsumption = annualEnergyConsumption;
	}
	public int getNumberOfPvModules(PvModule pvModule){
		double nominalPower = pvModule.getNominalPower()/1000;
		int numberOfPvModules = (int)Math.ceil(matchSystemPower()/nominalPower);
		return numberOfPvModules;
	}
	
	public double matchSystemPower() {
		return matchSystemPowerWithoutDcToAcLossess()*DC_TO_AC_POWER_LOSSESS_COEFFICIENT;
	}

	private double matchSystemPowerWithoutDcToAcLossess() {
		return calculateAverageDailyEnergyConsumption()/calculatePeakSunHours();
		
	}
	private double calculateAverageDailyEnergyConsumption() {
		return annualEnergyConsumption/NUMBER_OF_DAYS_PER_ONE_YEAR;
	}
	private double calculatePeakSunHours() {
		return calculateIrradiancePerYear()/NUMBER_OF_DAYS_PER_ONE_YEAR;
	}
	private double calculateIrradiancePerYear() {
		return this.meteorologicalYear.stream().map(Weather::getIrradiance).reduce((a,b)->a+b).orElse(0.0)/1000;
	}



}
