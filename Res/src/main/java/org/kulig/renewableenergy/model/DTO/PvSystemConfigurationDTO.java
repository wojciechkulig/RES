package org.kulig.renewableenergy.model.DTO;

import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.TariffGroup;

public class PvSystemConfigurationDTO {
	private double annualEnergyConsumption;
	private  TariffGroup tariffGroup;
	private PvModule pvModule;
	public double getAnnualEnergyConsumption() {
		return annualEnergyConsumption;
	}
	public void setAnnualEnergyConsumption(double annualEnergyConsumption) {
		this.annualEnergyConsumption = annualEnergyConsumption;
	}
	public TariffGroup getTariffGroup() {
		return tariffGroup;
	}
	public void setTariffGroup(TariffGroup tariffGroup) {
		this.tariffGroup = tariffGroup;
	}
	public PvModule getPvModule() {
		return pvModule;
	}
	public void setPvModule(PvModule pvModule) {
		this.pvModule = pvModule;
	}
	

	
	

}
