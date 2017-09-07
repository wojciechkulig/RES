package org.kulig.renewableenergy.model.PowerOutputDevices;

import org.kulig.renewableenergy.model.entities.Weather;

public interface DevicePowerOutput {
	public double calculatePower(Weather weather);
	public double getNominalPower();

}
