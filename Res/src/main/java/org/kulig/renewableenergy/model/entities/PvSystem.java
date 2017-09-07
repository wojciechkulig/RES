package org.kulig.renewableenergy.model.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PvSystem {
	@Autowired
	private PvModule pvModule;
	private int numberOfModules;
	@Autowired
	private Inverter inverter;
	public PvSystem(PvModule pvModule,int numberOfModules, Inverter inverter){
		this.pvModule = pvModule;
		this.numberOfModules = numberOfModules;
		this.inverter = inverter;
	}
	public PvSystem(){
		
	}
	public PvModule getPvModule() {
		return pvModule;
	}
	public void setPvModule(PvModule pvModule) {
		this.pvModule = pvModule;
	}
	public int getNumberOfModules() {
		return numberOfModules;
	}
	public void setNumberOfModules(int numberOfModules) {
		this.numberOfModules = numberOfModules;
	}
	public Inverter getInverter() {
		return inverter;
	}
	public void setInverter(Inverter inverter) {
		this.inverter = inverter;
	}
	public double getPvSystemPower(){
		return pvModule.getNominalPower()*numberOfModules;
	}
	

}
