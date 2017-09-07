package org.kulig.renewableenergy.model.PvSystem.PvModule;

import org.kulig.renewableenergy.model.entities.PvModule;

public class PvModuleVoltageProperties {
	private static final double MODULE_TEMPERATURE_STC = 25.0;
	private PvModule pvModule;

	public PvModuleVoltageProperties(PvModule pvModule) {
		this.pvModule = pvModule;
	}

	public double calculateVmppAtGivenTemperature(double moduleTemperature) {
		double vmppAtGivenTemperature = pvModule.getVmppSTC()
				- (moduleTemperature - MODULE_TEMPERATURE_STC) * calculateVoltageChangePerOneK();
		return vmppAtGivenTemperature;
	}

	public double calculateVocAtGivenTemperature(double moduleTemperature) {
		double vocAtGivenTemperature = pvModule.getVocSTC()
				- (moduleTemperature - MODULE_TEMPERATURE_STC) * calculateVoltageChangePerOneK();
		return vocAtGivenTemperature;
	}

	private double calculateVoltageChangePerOneK() {
		double VoltageChangePerOneK = (this.pvModule.getVocSTC() * this.pvModule.getTemperatureCoefficientOfVoc())
				/ 100;
		return VoltageChangePerOneK;

	}

	public double calculateOperatingVoltage() {
		double operatingVoltage = pvModule.getVmppSTC() - MODULE_TEMPERATURE_STC * calculateVoltageChangePerOneK();
		return operatingVoltage;
	}

	public PvModule getPvModule() {
		return pvModule;
	}

	public void setPvModule(PvModule pvModule) {
		this.pvModule = pvModule;
	}

}
