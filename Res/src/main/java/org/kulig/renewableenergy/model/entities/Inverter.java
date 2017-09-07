package org.kulig.renewableenergy.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="Inverters")
public class Inverter {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(name="nominal_power_AC")
	private double nominalPowerAC;
	@Column(name="max_input_voltage")
	private double maxInputVoltage;	
	@Column(name="min_input_voltage")
	private double minVoltageMPP;
	@Column(name="max_voltage_mpp")
	private double maxVoltageMPP;
	@Column(name="nominal_voltage_dc")
	private double nominalVoltageDC;
	@Value(value="0.965")
	@Column(name="efficiency_euro")
	private double efficiencyEuro;
	@Column(name="max_input_current")
	private double maxInputCurrent;
	@Column(name="number_of_dc_inputs")
	private int numberOfDcInputs;
	@Column(name="three_phase")
	private boolean threePhase; 
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getNominalPowerAC() {
		return nominalPowerAC;
	}
	public void setNominalPowerAC(double nominalPowerAC) {
		this.nominalPowerAC = nominalPowerAC;
	}

	public double getMaxInputVoltage() {
		return maxInputVoltage;
	}
	public void setMaxInputVoltage(double maxInputVoltage) {
		this.maxInputVoltage = maxInputVoltage;
	}
	public double getMinVoltageMPP() {
		return minVoltageMPP;
	}
	public void setMinVoltageMPP(double minVoltageMPP) {
		this.minVoltageMPP = minVoltageMPP;
	}
	public double getMaxVoltageMPP() {
		return maxVoltageMPP;
	}
	public void setMaxVoltageMPP(double maxVoltageMPP) {
		this.maxVoltageMPP = maxVoltageMPP;
	}
	public double getNominalVoltageDC() {
		return nominalVoltageDC;
	}
	public void setNominalVoltageDC(double nominalVoltageDC) {
		this.nominalVoltageDC = nominalVoltageDC;
	}
	public double getEfficiencyEuro() {
		return efficiencyEuro;
	}
	public void setEfficiencyEuro(double efficiencyEuro) {
		this.efficiencyEuro = efficiencyEuro;
	}
	public double getMaxInputCurrent() {
		return maxInputCurrent;
	}
	public void setMaxInputCurrent(double maxInputCurrent) {
		this.maxInputCurrent = maxInputCurrent;
	}

	public int getNumberOfDcInputs() {
		return numberOfDcInputs;
	}
	public void setNumberOfDcInputs(int numberOfDcInputs) {
		this.numberOfDcInputs = numberOfDcInputs;
	}
	public boolean isThreePhase() {
		return threePhase;
	}
	public void setThreePhase(boolean threePhase) {
		this.threePhase = threePhase;
	}
	
	

}
