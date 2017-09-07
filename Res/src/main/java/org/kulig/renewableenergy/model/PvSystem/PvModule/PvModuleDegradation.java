package org.kulig.renewableenergy.model.PvSystem.PvModule;

import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.PvModulePeformanceGuarantee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PvModuleDegradation {
	@Autowired
	private PvModule pvModule;
	private PvModulePeformanceGuarantee firstPhaseWarranty;
	private PvModulePeformanceGuarantee secondPhaseWarranty;
	@Value(value="0")
	private int pvModuleYear;

	public PvModuleDegradation(PvModule pvModule, int pvModuleYear) {
		this.pvModule = pvModule;
		this.pvModuleYear = pvModuleYear;	
	}
	public double calculateModulePowerDegradation(int year){
		pvModuleYear = year;
		return calculateModulePowerDegradation();
	}

	public double calculateModulePowerDegradation() {	
		this.firstPhaseWarranty = pvModule.getPvModulePowerOutputWarrantyPeriods().get(0);
		if(pvModuleYear == 0){
			return 0;
		}else if(pvModuleYear <= firstPhaseWarranty.getPerformanceGuaranteePeriod()) {
			return calculateOnePhaseModulePowerDegradation(pvModuleYear, firstPhaseWarranty);
		} else {
			return calculateTwoPhaseModulePowerDegradation();
		}
	}

	private double calculateTwoPhaseModulePowerDegradation() {
		double firstPhaseDegradation = calculateOnePhaseModulePowerDegradation(
				firstPhaseWarranty.getPerformanceGuaranteePeriod(), firstPhaseWarranty);

		secondPhaseWarranty = pvModule.getPvModulePowerOutputWarrantyPeriods().get(1);

		double secondPhaseDegradation = calculateOnePhaseModulePowerDegradation(
				pvModuleYear - firstPhaseWarranty.getPerformanceGuaranteePeriod(), secondPhaseWarranty);
		return firstPhaseDegradation + secondPhaseDegradation;
	}

	private double calculateOnePhaseModulePowerDegradation(int year,PvModulePeformanceGuarantee pvModulePowerOutputWarranty){
		isPowerOutputWarrancyPeriodLongerThanModuleAge(year, pvModulePowerOutputWarranty);
		
		if (pvModulePowerOutputWarranty.isDegradationTypeLinear()) {
			return calculateLinearDegradation(year, pvModulePowerOutputWarranty.getModulePercentageDegradation())/100;
		} else {
			return pvModulePowerOutputWarranty.getModulePercentageDegradation()/100;
		}

	}
	

	private void isPowerOutputWarrancyPeriodLongerThanModuleAge(int year,
			PvModulePeformanceGuarantee pvModulePowerOutputWarranty) {
		if(year > pvModulePowerOutputWarranty.getPerformanceGuaranteePeriod()){
			throw new RuntimeException("Module age is greater than module performance guarantee!");
		}
		
	}

	private double calculateLinearDegradation(int year, double moduleAnnualDegradation) {
		double ModulePercentageDegradation = moduleAnnualDegradation * year;
		return ModulePercentageDegradation;
	}
}
