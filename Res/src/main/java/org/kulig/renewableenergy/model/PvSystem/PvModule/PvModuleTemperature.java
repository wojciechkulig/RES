package org.kulig.renewableenergy.model.PvSystem.PvModule;

import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.Weather;

public class PvModuleTemperature {
	private static final double AMBIENT_TEMPERATURE_NOCT = 20.0;
	private static final double IRRADIANCE_NOCT = 800.0;
	private static final double TRANSMITANCE_TIMES_ABSORPTVITY = 0.9;
	private PvModule pvModule;
	
	public PvModuleTemperature(PvModule pvModule) {
		this.pvModule = pvModule;
	}
	
	//Calculate Module Temperature based on Duffie-Beckman model
	public double calculateModuleTemperature(Weather weather) {
		double NOCT = this.pvModule.getNOCT();
		double irradiance = weather.getIrradiance();
		double ambientTemperature = weather.getDBT();
		double windSpeed = weather.getWS(); 
		double moduleTemperature = ambientTemperature
				+ ((NOCT - AMBIENT_TEMPERATURE_NOCT)
						*(irradiance / IRRADIANCE_NOCT)
						*(9.5/(5.7 + 3.8*windSpeed))
						*(1-(pvModule.getModuleEfficiencySTC()/TRANSMITANCE_TIMES_ABSORPTVITY)));
		return moduleTemperature;
	}

	public PvModule getPvModule() {
		return pvModule;
	}

	public void setPvModule(PvModule pvModule) {
		this.pvModule = pvModule;
	}

}