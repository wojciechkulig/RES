package org.kulig.renewableenergy.Energy;

import java.util.List;

import org.kulig.renewableenergy.model.entities.Weather;

public class AnnualEnergyBilanceInputData {
	private List<Double> hourlyEnergyConsumption;
	private List<Double> hourlyEnergyYield;
	private List<Weather> hourlyWeatherConditions;
	private int numberOfSettlementPeriods;
	private double correctionFactor;
	
	public List<Double> getHourlyEnergyConsumption() {
		return hourlyEnergyConsumption;
	}
	public void setHourlyEnergyConsumption(List<Double> hourlyEnergyConsumption) {
		this.hourlyEnergyConsumption = hourlyEnergyConsumption;
	}
	public List<Double> getHourlyEnergyYield() {
		return hourlyEnergyYield;
	}
	public void setHourlyEnergyYield(List<Double> hourlyEnergyYield) {
		this.hourlyEnergyYield = hourlyEnergyYield;
	}
	public int getNumberOfSettlementPeriods() {
		return numberOfSettlementPeriods;
	}
	public void setNumberOfSettlementPeriods(int numberOfSettlementPeriods) {
		this.numberOfSettlementPeriods = numberOfSettlementPeriods;
	}
	public double getCorrectionFactor() {
		return correctionFactor;
	}
	public void setCorrectionFactor(double correctionFactor) {
		this.correctionFactor = correctionFactor;
	}
	public List<Weather> getHourlyWeatherConditions() {
		return hourlyWeatherConditions;
	}
	public void setHourlyWeatherConditions(List<Weather> hourlyWeatherConditions) {
		this.hourlyWeatherConditions = hourlyWeatherConditions;
	}
	
	
	

}
