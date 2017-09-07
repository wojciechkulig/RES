package org.kulig.renewableenergy.model.PvSystem.PvModule;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kulig.renewableenergy.model.PvSystem.PvModule.PvModuleDegradation;
import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.PvModulePeformanceGuarantee;


public class PvModuleDegradationTests {
	

	private static PvModule pvModule = new PvModule();
	private static PvModuleDegradation pvModuleDegradation;
	@Before
	public void setUp(){
		pvModule.setPvModulePowerOutputWarrantyPeriods(getExamplePowerOutputWarrantyPeriods());
	}
	
	@Test
	public void testCalculateModulePowerDegradation_CaseModuleAgeZero(){
		pvModuleDegradation = new PvModuleDegradation(pvModule, 0);
		double moduleDegradation = pvModuleDegradation.calculateModulePowerDegradation();		
		assertEquals(0,moduleDegradation,0);
	}
	
	@Test
	public void testCalculateModulePowerDegradation_CaseOnePhaseLinear(){
		pvModuleDegradation = new PvModuleDegradation(pvModule, 4);
		double moduleDegradation = pvModuleDegradation.calculateModulePowerDegradation();		
		assertEquals(0.16,moduleDegradation,0.01);
	}
	
	@Test
	public void testCalculateModulePowerDegradation_CaseOnePhaseStandard(){
		pvModule.getPvModulePowerOutputWarrantyPeriods().set(0, getStandardPowerOutputWarranty());
		pvModuleDegradation = new PvModuleDegradation(pvModule, 4);
		double moduleDegradation = pvModuleDegradation.calculateModulePowerDegradation();		
		assertEquals(0.0344,moduleDegradation,0);
	}
	
	@Test
	public void testCalculateModulePowerDegradation_CaseTwoPhaseFirstLinearSecondStandard(){
		pvModuleDegradation = new PvModuleDegradation(pvModule, 6);
		double moduleDegradation = pvModuleDegradation.calculateModulePowerDegradation();		
		assertEquals(0.2344,moduleDegradation,0.01);
	}
	
	@Test
	public void testCalculateModulePowerDegradation_CaseModuleAgeEqualThanPerformanceGuaranteePeriod(){
		pvModuleDegradation = new PvModuleDegradation(pvModule, 20);
		double moduleDegradation = pvModuleDegradation.calculateModulePowerDegradation();
		assertEquals(0.2344,moduleDegradation,0.01);
	}
	
	@Test(expected=RuntimeException.class)
	public void testCalculateModulePowerDegradation_CaseModuleAgeGreaterThanPerformanceGuaranteePeriod(){
		pvModuleDegradation = new PvModuleDegradation(pvModule, 21);
		pvModuleDegradation.calculateModulePowerDegradation();		
	}

	
	private PvModulePeformanceGuarantee getStandardPowerOutputWarranty(){
		PvModulePeformanceGuarantee standardWarranty = new PvModulePeformanceGuarantee();
		standardWarranty.setPerformanceGuaranteePeriod(15);
		standardWarranty.setDegradationTypeLinear(false);
		standardWarranty.setModulePercentageDegradation(3.44);
		return standardWarranty;
	}
	
	private static List<PvModulePeformanceGuarantee> getExamplePowerOutputWarrantyPeriods(){
		PvModulePeformanceGuarantee firstPhaseWarranty = new PvModulePeformanceGuarantee();
		firstPhaseWarranty.setPerformanceGuaranteePeriod(5);
		firstPhaseWarranty.setDegradationTypeLinear(true);
		firstPhaseWarranty.setModulePercentageDegradation(4);
		PvModulePeformanceGuarantee secondPhaseWarranty = new PvModulePeformanceGuarantee();
		secondPhaseWarranty.setPerformanceGuaranteePeriod(15);
		secondPhaseWarranty.setDegradationTypeLinear(false);
		secondPhaseWarranty.setModulePercentageDegradation(3.44);
		List<PvModulePeformanceGuarantee> pvModulePowerOutputWarrantyPeriods = new LinkedList<>();
		pvModulePowerOutputWarrantyPeriods.add(firstPhaseWarranty);
		pvModulePowerOutputWarrantyPeriods.add(secondPhaseWarranty);
		
		return pvModulePowerOutputWarrantyPeriods;
	}

}
