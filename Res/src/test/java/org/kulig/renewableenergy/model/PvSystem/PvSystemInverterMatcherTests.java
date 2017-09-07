package org.kulig.renewableenergy.model.PvSystem;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.kulig.renewableenergy.model.PvSystem.PvSystemInverterMatcher;
import org.kulig.renewableenergy.model.entities.Inverter;
import org.kulig.renewableenergy.model.entities.PvModule;

public class PvSystemInverterMatcherTests {

	private static PvSystemInverterMatcher inverterMatcher;
	@Before
	public  void setUpBeforeClass() throws Exception {
		PvModule pvModule = new PvModule();
		pvModule.setNominalPower(250);
		pvModule.setIscSTC(8.7);
		pvModule.setVocSTC(37.8);
		pvModule.setImppSTC(8.3);
		pvModule.setTemperatureCoefficientOfVoc(0.31);
		pvModule.setVmppSTC(30.2);
		inverterMatcher = new PvSystemInverterMatcher(pvModule,24,false);	
	}

	@Test
	public void testGetMatchingInverters_CaseMatchingInverter() {
		boolean matching = inverterMatcher.isInverterMatching(getExampleMatchingInverter());
		assertEquals(true,matching);
	}

	@Test
	public void testGetMatchingInverters_CaseWrongPhase(){
		Inverter inverter = getExampleMatchingInverter();
		inverter.setThreePhase(true);
		boolean matching = inverterMatcher.isInverterMatching(inverter);
		assertEquals(false,matching);
		
	}
	
	@Test
	public void testGetMatchingInverters_CaseTooLowPower(){
		Inverter inverter = getExampleMatchingInverter();
		inverter.setNominalPowerAC(500);
		boolean matching = inverterMatcher.isInverterMatching(inverter);
		assertEquals(false,matching);
		
	}

	@Test
	public void testGetMatchingInverters_CaseMaxInputCurrentLowerThanOneStringCurrent(){
		Inverter inverter = getExampleMatchingInverter();
		inverter.setMaxInputVoltage(20);
		boolean matching = inverterMatcher.isInverterMatching(inverter);
		assertEquals(false,matching);
		
	}
	
	@Test
	public void testCalculateNumberOfModulesInOneString_CaseMaxNumberVOC(){
		Inverter inverter = getExampleMatchingInverter();
		int numberOfModulesInOneString = inverterMatcher.calculateNumberOfModulesInOneString(inverter);
		assertEquals(12,numberOfModulesInOneString);
	}
	
	@Test
	public void testCalculateNumberOfModulesInOneString_CaseInverterMaxInputVoltageLowerThanSingleModuleVoltage(){
		Inverter inverter = getExampleMatchingInverter();
		inverter.setMaxInputVoltage(20);
		int numberOfModulesInOneString = inverterMatcher.calculateNumberOfModulesInOneString(inverter);
		assertEquals(0,numberOfModulesInOneString);
	}
	
	@Test
	public void testCalculateNumberOfModulesInOneString_CaseMinAmountOfModulesNotMet(){
		Inverter inverter = getExampleMatchingInverter();
		inverterMatcher.setNumberOfPvModules(5);
		int numberOfModulesInOneString = inverterMatcher.calculateNumberOfModulesInOneString(inverter);
		assertEquals(0,numberOfModulesInOneString);
	}
	@Test
	public void testCalculateMinAmountOfModulesInOneString(){
		Inverter inverter = getExampleMatchingInverter();
		int numberOfModulesInOneString = inverterMatcher.calculateMinAmountOfModulesInOneString(inverter);
		assertEquals(9,numberOfModulesInOneString);
	}
	@Test
	public void testCalculateMaxNumberOfStrings_CaseMaxNumberOfStringsIscConditionEqualsNumberOfInverterDcInputs(){
		Inverter inverter = getExampleMatchingInverter();
		int maxNumberOfStrings = inverterMatcher.calculateMaxNumberOfStrings(inverter);
		assertEquals(2,maxNumberOfStrings);
	}
	@Test
	public void testCalculateMaxNumberOfStrings_CaseMaxNumberOfStringsIscConditionGreaterThanNumberOfInverterDcInputs(){
		Inverter inverter = getExampleMatchingInverter();
		inverter.setMaxInputCurrent(27);
		int maxNumberOfStrings = inverterMatcher.calculateMaxNumberOfStrings(inverter);
		assertEquals(2,maxNumberOfStrings);
	}
	@Test
	public void testCalculateMaxNumberOfStrings_CaseMaxNumberOfStringsIscConditionLowerThanNumberOfInverterDcInputs(){
		Inverter inverter = getExampleMatchingInverter();
		inverter.setMaxInputCurrent(10);
		int maxNumberOfStrings = inverterMatcher.calculateMaxNumberOfStrings(inverter);
		assertEquals(1,maxNumberOfStrings);
	}


	
	private static Inverter getExampleMatchingInverter(){
		Inverter inverter = new Inverter();
		inverter.setThreePhase(false);
		inverter.setNominalPowerAC(5500);
		inverter.setNominalVoltageDC(400);
		inverter.setMinVoltageMPP(220);
		inverter.setMaxVoltageMPP(500);
		inverter.setMaxInputCurrent(20);
		inverter.setMaxInputVoltage(550);
		inverter.setNumberOfDcInputs(2);
		return inverter;
	}

}
