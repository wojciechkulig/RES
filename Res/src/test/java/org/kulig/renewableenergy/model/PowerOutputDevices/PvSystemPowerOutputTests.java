package org.kulig.renewableenergy.model.PowerOutputDevices;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kulig.renewableenergy.model.PowerOutputDevices.PvSystemPowerOutput;
import org.kulig.renewableenergy.model.entities.Inverter;
import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.PvSystem;
import org.kulig.renewableenergy.model.entities.Weather;

public class PvSystemPowerOutputTests {
	private static PvSystemPowerOutput pvSystemPowerOutput;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PvModule module = new PvModule();
		module.setModuleSurface(1.64);
		module.setModuleEfficiencySTC(0.186);
		module.setNOCT(45.0);
		module.setTemperatureCoefficientOfPmax(0.41);
		Inverter inverter = new Inverter();
		inverter.setEfficiencyEuro(0.95);
		int numberOfModules = 20;
		PvSystem pvSystem = new PvSystem(module,numberOfModules,inverter);
		pvSystemPowerOutput = new PvSystemPowerOutput(pvSystem);
	}

	@Test
	public void testCalculatePower_CaseCondtionsNoct() {
		double powerOutput = pvSystemPowerOutput.calculatePower(getWeatherConditionsNOCT());
		assertEquals(4256,powerOutput,1);
	}
	
	private Weather getWeatherConditionsNOCT(){
		Weather weather = new Weather();
		weather.setDBT(20);
		weather.setIrradiance(800);
		return weather;
	}

}
