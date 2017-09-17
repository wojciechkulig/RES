package org.kulig.renewableenergy.Energy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.kulig.renewableenergy.model.PowerOutputDevices.DevicePowerOutput;
import org.kulig.renewableenergy.model.PvSystem.PvModule.PvModuleDegradation;
import org.kulig.renewableenergy.model.entities.HourlyEnergyConsumption;
import org.kulig.renewableenergy.model.entities.PvSystemEnergyBilance;
import org.kulig.renewableenergy.model.entities.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContextClassOfAnnualEnergyBilanceStrategy {
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

	public ContextClassOfAnnualEnergyBilanceStrategy(DevicePowerOutput devicePowerOutput,
			List<Weather> hourlyWeatherConditions, List<HourlyEnergyConsumption> hourlyEnergyConsumption) {
		lifeTimePeriod = 15;
		annualIncreaseInEnergyDemand = 1.009;
		this.devicePowerOutput = devicePowerOutput;
		this.hourlyWeatherConditions =  hourlyWeatherConditions;
		this.hourlyEnergyConsumptionFirstYear =  hourlyEnergyConsumption;
		

	}
	public List<List<PvSystemEnergyBilance>> calculateLifeTimeEnergyBalance(int numberOfSettlementPeriods, String tariffGroup){
		
		calculateHourlyEnergyYield();
		List<List<PvSystemEnergyBilance>> yearlyEnergyDistribution = new ArrayList<>();
		for(int i=0 ; i<lifeTimePeriod; i++){
			setSystemStateForYear(i);
			yearlyEnergyDistribution.add(createStrategy(tariffGroup).calculateAnnualEnergyBalance(getAnnualEnergyBilanceData(numberOfSettlementPeriods)));
		}
		return yearlyEnergyDistribution;
	}

	//w rozwijanlej liscie musi byc String, ktory jasno okresli jaka to jest nazwa grupy taryfowej np G12Tauron, G12WEnerga itp.
	private AnnualEnergyBilanceStrategy createStrategy(String tariffGroup) {
		if(tariffGroup.equals("G11")){
			return new G11AnnualEnergyBilanceStrategy();
		}
		if(tariffGroup.equals("G12 Energa")){
			return new G12EnergaEnergyBilanceStrategy();
		}
		if(tariffGroup.equals("G12 Innogy")){
			return new G12InnogyEnergyBilanceStrategy();
		}
		if(tariffGroup.equals("G12 PGE")){
			return new G12PgeEnergyBilanceStrategy();
		}
		if(tariffGroup.equals("G12 Tauron")){
			return new G12TauronEnergyBilanceStrategy();
		}
		if(tariffGroup.equals("G12W Energa")){
			return new G12WEnergaEnergyBilanceStrategy();
		}
		if(tariffGroup.equals("G12W Innogy")){
			return new G12WInnogyEnergyBilanceStrategy();
		}
		if(tariffGroup.equals("G12W PGE")){
			return new G12WPgeEnergyBilanceStrategy();
		}
		if(tariffGroup.equals("G12W Tauron")){
			return new G12WTauronEnergyBilanceStrategy();
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
		return data;
	}
	private double getCorrectionFactor() {
		if(devicePowerOutput.getNominalPower() >10000){
			return CORRECTION_FACTOR_MORE_THAN_10KW;
		}
		return CORRECTION_FACTOR_LESS_THAN_10KW;
	}


	

}
