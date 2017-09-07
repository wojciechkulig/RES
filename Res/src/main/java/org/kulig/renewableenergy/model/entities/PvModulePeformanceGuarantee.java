package org.kulig.renewableenergy.model.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PvModulePeformanceGuarantee {
	@Column(name="degradation_phase")
	private int degradationPhase;
	
	@Column(name="performance_guarantee_period")
	private int peformanceGuaranteePeriod;
	@Column(name="degradation_type_linear")
	private boolean degradationTypeLinear;
	
	//standard or linear
	@Column(name="module_percentage_degradation")
	private double modulePercentageDegradation;

	public int getDegradationPhase() {
		return degradationPhase;
	}

	public void setDegradationPhase(int degradationPhase) {
		this.degradationPhase = degradationPhase;
	}

	public int getPerformanceGuaranteePeriod() {
		return peformanceGuaranteePeriod;
	}

	public void setPerformanceGuaranteePeriod(int degradationPeriod) {
		this.peformanceGuaranteePeriod = degradationPeriod;
	}

	public boolean isDegradationTypeLinear() {
		return degradationTypeLinear;
	}

	public void setDegradationTypeLinear(boolean degradationTypeLinear) {
		this.degradationTypeLinear = degradationTypeLinear;
	}

	public double getModulePercentageDegradation() {
		return modulePercentageDegradation;
	}

	public void setModulePercentageDegradation(double modulePercentageDegradation) {
		this.modulePercentageDegradation = modulePercentageDegradation;
	}
	

	

	
	
	
}
