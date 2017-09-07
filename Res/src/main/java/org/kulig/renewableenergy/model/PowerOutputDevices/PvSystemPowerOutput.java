package org.kulig.renewableenergy.model.PowerOutputDevices;

import org.kulig.renewableenergy.model.entities.PvSystem;
import org.kulig.renewableenergy.model.entities.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PvSystemPowerOutput implements DevicePowerOutput {

	private static final double wireLosses = 0.01;
	private static final double shadowingLosses = 0.015;
	private static final double lowIrradianceLosses = 0.03;
	private static final double currentMismatchLosses = 0.01;
	private static final double diodeLosses = 0.005;
	private PvModulePowerOutput pvModulePowerOutput;
	@Autowired
	private PvSystem pvSystem;

	public PvSystemPowerOutput(PvSystem pvSystem) {
		this.pvSystem = pvSystem;
		pvModulePowerOutput = new PvModulePowerOutput(pvSystem.getPvModule());
	}

	@Override
	public double calculatePower(Weather weather) {
		return pvModulePowerOutput.calculatePower(weather) *pvSystem.getNumberOfModules()* getPvSystemLossessOtherThanHighPvModuleTemperatureLosses();

	}

	private double getPvSystemLossessOtherThanHighPvModuleTemperatureLosses() {
		return 1- (wireLosses + shadowingLosses + lowIrradianceLosses + currentMismatchLosses + diodeLosses
				+ (1 - pvSystem.getInverter().getEfficiencyEuro()));
	}

	@Override
	public double getNominalPower() {
		return pvSystem.getPvSystemPower();
	}

}
