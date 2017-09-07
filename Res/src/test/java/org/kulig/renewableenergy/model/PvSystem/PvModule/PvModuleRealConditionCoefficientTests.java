package org.kulig.renewableenergy.model.PvSystem.PvModule;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kulig.renewableenergy.model.PvSystem.PvModule.PvModuleRealConditionCoefficient;
import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.Weather;

public class PvModuleRealConditionCoefficientTests {

	private static PvModuleRealConditionCoefficient pvModuleRealConditionCoefficient;
	private static Weather weather;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PvModule module = new PvModule();
		module.setModuleSurface(1.64);
		module.setModuleEfficiencySTC(0.186);
		module.setNOCT(45.0);
		module.setTemperatureCoefficientOfPmax(0.41);
		weather = new Weather();
		weather.setIrradiance(1000.0);
		weather.setDBT(25);
		pvModuleRealConditionCoefficient = new PvModuleRealConditionCoefficient(module,weather);
	}

	@Test
	public void testCalculatePowerTemperatureCoefficient_CaseConditionsIrradiance1000Tambieent25() {
		double temperatureCoefficient = pvModuleRealConditionCoefficient.calculatePowerTemperatureCoefficient();
		assertEquals(0.87, temperatureCoefficient, 0.01);
	}

	@Test
	public void testCalculatePowerTemperatureCoefficient_CaseConditionsNOCT() {
		pvModuleRealConditionCoefficient.setWeather(getWeatherConditionsNOCT());
		double temperatureCoefficient = pvModuleRealConditionCoefficient.calculatePowerTemperatureCoefficient();
		assertEquals(0.91, temperatureCoefficient, 0.01);
	}

	@Test
	public void testCalculatePowerTemperatureCoefficient_CaseModuleTemperatureLowerThan25oC() {
		pvModuleRealConditionCoefficient.setWeather(getColdWeatherConditions());
		double temperatureCoefficient = pvModuleRealConditionCoefficient.calculatePowerTemperatureCoefficient();
		assertEquals(1.10, temperatureCoefficient, 0.01);
	}

	private Weather getColdWeatherConditions() {
		Weather weather = new Weather();
		weather.setDBT(-25);
		weather.setIrradiance(800);
		return weather;
	}
	private Weather getWeatherConditionsNOCT(){
		Weather weather = new Weather();
		weather.setDBT(20);
		weather.setIrradiance(800);
		return weather;
	}

}
