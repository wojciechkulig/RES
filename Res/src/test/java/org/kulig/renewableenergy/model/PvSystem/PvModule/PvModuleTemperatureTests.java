package org.kulig.renewableenergy.model.PvSystem.PvModule;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kulig.renewableenergy.model.PvSystem.PvModule.PvModuleTemperature;
import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.Weather;

public class PvModuleTemperatureTests {
	private static PvModuleTemperature pvModuleTemperature;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PvModule module = new PvModule();
		module.setModuleSurface(1.64);
		module.setModuleEfficiencySTC(0.186);
		module.setNOCT(45.0);
		module.setTemperatureCoefficientOfPmax(0.41);
		Weather weather = new Weather();
		weather.setIrradiance(1000.0);
		weather.setDBT(25);
		pvModuleTemperature = new PvModuleTemperature(module);
	}

	@Test
	public void testCalculateModuleTemperature_CaseConditionsIrradiance1000Tambieent25(){	
		Weather weather = new Weather();
		weather.setIrradiance(1000.0);
		weather.setDBT(25);
		weather.setWS(1);
		double panelTemperature = pvModuleTemperature.calculateModuleTemperature(weather);
		assertEquals(49.79, panelTemperature,0.1);
	}
	
	@Test
	public void testCalculateModuleTemperature_CaseConditionsNOCT(){		
		Weather weather = getWeatherConditionsNOCT();
		double panelTemperature = pvModuleTemperature.calculateModuleTemperature(weather);
		assertEquals(39.83, panelTemperature,0.1);
	}
	
	private  Weather getWeatherConditionsNOCT(){
		Weather weather = new Weather();
		weather.setWS(1);
		weather.setDBT(20);
		weather.setIrradiance(800);
		return weather;
	}

}
