package org.kulig.renewableenergy.Energy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.kulig.renewableenergy.model.PowerOutputDevices.DevicePowerOutput;
import org.kulig.renewableenergy.model.PvSystem.PvModule.PvModuleDegradation;
import org.kulig.renewableenergy.model.entities.HourlyEnergyConsumption;
import org.kulig.renewableenergy.model.entities.PvSystemEnergyDistribution;
import org.kulig.renewableenergy.model.entities.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnergyDistributionCalculator {
	private static final int ANNUAL_NUMBER_OF_HOURS = 8760;
	private static final double CORRECTION_FACTOR_LESS_THAN_10KW = 0.8;
	private static final double CORRECTION_FACTOR_MORE_THAN_10KW = 0.7;
	@Autowired
	private DevicePowerOutput devicePowerOutput;
	@Autowired
	private List<Weather> hourlyWeatherConditions;
	@Autowired
	private List<HourlyEnergyConsumption> hourlyEnergyConsumptionFirstYear;
	@Autowired
	private PvModuleDegradation pvModuleDegradation;
	
	private int lifeTimePeriod;
	private double annualIncreaseInEnergyDemand;
	private List<Double> hourlyEnergyConsumption;
	private List<Double> hourlyEnergyYieldFirstYear;
	private List<Double> hourlyEnergyYield;

	public EnergyDistributionCalculator(DevicePowerOutput devicePowerOutput,
			List<Weather> hourlyWeatherConditions, List<HourlyEnergyConsumption> hourlyEnergyConsumption) {
		lifeTimePeriod = 15;
		annualIncreaseInEnergyDemand = 1.009;
		this.devicePowerOutput = devicePowerOutput;
		this.hourlyWeatherConditions =  hourlyWeatherConditions;
		this.hourlyEnergyConsumptionFirstYear =  hourlyEnergyConsumption;
		

	}
	public List<List<PvSystemEnergyDistribution>> calculateLifeTimeEnergyBalance(int numberOfSettlementPeriods, String tariffGroup){
		
		calculateHourlyEnergyYield();
		List<List<PvSystemEnergyDistribution>> yearlyEnergyDistribution = new ArrayList<>();
		for(int i=0 ; i<lifeTimePeriod; i++){
			setSystemStateForYear(i);
			yearlyEnergyDistribution.add(createStrategy(tariffGroup).calculateAnnualEnergyBalance(getAnnualEnergyBilanceData(numberOfSettlementPeriods)));
		}
		return yearlyEnergyDistribution;
	}

	//tutaj bedzie sposob juz konkretny czyli np. g12 tauron
	private AnnualEnergyBilanceStrategy createStrategy(String tariffGroup) {
		if(tariffGroup.equals("G11")){
			return new G11AnnualEnergyBilanceStrategy();
		}
		return null;
		
	}
	private void calculateHourlyEnergyYield() {
		hourlyEnergyYieldFirstYear = new ArrayList<>(ANNUAL_NUMBER_OF_HOURS);
		for (Weather weather : hourlyWeatherConditions) {
			hourlyEnergyYieldFirstYear.add(devicePowerOutput.calculatePower(weather));
		}
	}
	private void setSystemStateForYear(int year){
		hourlyEnergyYield = hourlyEnergyYieldFirstYear.stream().map(h->h*(1-pvModuleDegradation.calculateModulePowerDegradation(year))).collect(Collectors.toList());
		hourlyEnergyConsumption = hourlyEnergyConsumptionFirstYear.stream().map(h -> h.getHourly_Energy_Consumption()*Math.pow(annualIncreaseInEnergyDemand, year)).collect(Collectors.toList());
	}
	private AnnualEnergyBilanceInputData getAnnualEnergyBilanceData(int numberOfSettlementPeriods) {
		AnnualEnergyBilanceInputData data = new AnnualEnergyBilanceInputData();
		data.setCorrectionFactor(getCorrectionFactor());
		data.setHourlyEnergyConsumption(hourlyEnergyConsumption);
		data.setHourlyEnergyYield(hourlyEnergyYield);
		data.setHourlyWeatherConditions(hourlyWeatherConditions);
		data.setNumberOfSettlementPeriods(numberOfSettlementPeriods);
		return null;
	}
	private double getCorrectionFactor() {
		if(devicePowerOutput.getNominalPower() >10000){
			return CORRECTION_FACTOR_MORE_THAN_10KW;
		}
		return CORRECTION_FACTOR_LESS_THAN_10KW;
	}


	

}
