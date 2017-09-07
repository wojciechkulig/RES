package org.kulig.renewableenergy.model.PowerOutputDevices;

import org.kulig.renewableenergy.model.PvSystem.PvModule.PvModuleRealConditionCoefficient;
import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.Weather;

public class PvModulePowerOutput implements DevicePowerOutput {

	private PvModule pvModule;
	private PvModuleRealConditionCoefficient pvModuleRealConditionCoefficient;

	public PvModulePowerOutput(PvModule pvModule) {
		this.pvModule = pvModule;
	}

	public double calculatePower(Weather weather) {
		pvModuleRealConditionCoefficient = new PvModuleRealConditionCoefficient(this.pvModule, weather);
		double power = calculatePowerSTC(weather) * pvModuleRealConditionCoefficient.calculateRealConditionsCoefficient();
		return power;

	}

	public double calculatePowerSTC(Weather weather) {
		double irradiance = weather.getIrradiance();
		double moduleEfficiencySTC = this.pvModule.getModuleEfficiencySTC();
		double moduleSurface = this.pvModule.getModuleSurface();
		double modulePowerSTC = moduleEfficiencySTC * moduleSurface * irradiance;
		return modulePowerSTC;

	}

	public PvModule getPvModule() {
		return this.pvModule;
	}

	public void setPvModule(PvModule pvModule) {
		this.pvModule = pvModule;
	}

	@Override
	public double getNominalPower() {
		return pvModule.getNominalPower();
	}



}
