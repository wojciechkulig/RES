package org.kulig.renewableenergy.model.entities;

import javax.persistence.Embeddable;

@Embeddable
public class HourlyEnergyConsumption {
	private int Hour;
	private double Hourly_Energy_Consumption;
	public int getHour() {
		return Hour;
	}
	public void setHour(int hour) {
		Hour = hour;
	}
	public double getHourly_Energy_Consumption() {
		return Hourly_Energy_Consumption;
	}
	public void setHourly_Energy_Consumption(double hourly_Energy_Consumption) {
		Hourly_Energy_Consumption = hourly_Energy_Consumption;
	}


	

}
