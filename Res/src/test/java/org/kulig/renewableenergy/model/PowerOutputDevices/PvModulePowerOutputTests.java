package org.kulig.renewableenergy.model.PowerOutputDevices;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.kulig.renewableenergy.model.PowerOutputDevices.PvModulePowerOutput;
import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.Weather;

public class PvModulePowerOutputTests {

	private static PvModulePowerOutput pvModulePowerOutput;
	private static Weather weather;
	// model lG 305W
	@Before
	public void before() {
		PvModule module = new PvModule();
		module.setModuleSurface(1.64);
		module.setModuleEfficiencySTC(0.186);
		module.setNOCT(45.0);
		module.setTemperatureCoefficientOfPmax(0.41);
		weather = new Weather();
		weather.setIrradiance(1000.0);
		weather.setDBT(25);
		pvModulePowerOutput = new PvModulePowerOutput(module);
	}

	@Test
	public void testCalculatePower_CaseConditionsIrradiance1000Tambieent25() {
		double power = pvModulePowerOutput.calculatePower(weather);
		assertEquals(266.0, power, 1.0);

	}
	
	@Test
	public void testCalculatePower_CaseConditionsNOCT() {
		double power = pvModulePowerOutput.calculatePower(getWeatherConditionsNOCT());		
		assertEquals(224.0, power, 1);
	}

	@Test
	public void testCalculatePowerSTC_CaseConditionsIrradiance1000Tambieent25() {
		double powerSTC = pvModulePowerOutput.calculatePowerSTC(weather);
		assertEquals(305, powerSTC, 1);
	}
	
	private Weather getWeatherConditionsNOCT(){
		Weather weather = new Weather();
		weather.setDBT(20);
		weather.setIrradiance(800);
		return weather;
	}





}
