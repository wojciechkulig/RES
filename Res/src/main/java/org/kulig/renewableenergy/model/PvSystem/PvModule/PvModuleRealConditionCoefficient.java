package org.kulig.renewableenergy.model.PvSystem.PvModule;

import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.Weather;

public class PvModuleRealConditionCoefficient {
	private static final double MODULE_TEMPERATURE_STC = 25.0;
	private PvModuleTemperature pvModuleTemperature;
	private PvModule pvModule;
	private Weather weather;
	
	public PvModuleRealConditionCoefficient(PvModule pvModule, Weather weather){
		this.pvModule = pvModule;
		this.weather = weather;
		pvModuleTemperature = new PvModuleTemperature(pvModule);
	}
	// here insert additional coefficients like shadowing etc
	public double calculateRealConditionsCoefficient() {
		double realConditionsCoefficient = calculatePowerTemperatureCoefficient();
		return realConditionsCoefficient;
	}

	public double calculatePowerTemperatureCoefficient() {
		double temperatureCoefficientOfPmax = this.pvModule.getTemperatureCoefficientOfPmax();
		double moduleTemperature = this.pvModuleTemperature.calculateModuleTemperature(weather);
		double temperatureCoefficient = 1.0
				- ((moduleTemperature - MODULE_TEMPERATURE_STC) * temperatureCoefficientOfPmax) / 100;
		return temperatureCoefficient;
	}
	public Weather getWeather() {
		return weather;
	}
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	
}
