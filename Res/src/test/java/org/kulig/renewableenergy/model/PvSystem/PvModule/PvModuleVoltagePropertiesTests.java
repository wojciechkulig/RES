package org.kulig.renewableenergy.model.PvSystem.PvModule;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.kulig.renewableenergy.model.PvSystem.PvModule.PvModuleVoltageProperties;
import org.kulig.renewableenergy.model.entities.PvModule;

public class PvModuleVoltagePropertiesTests {
	
	private static PvModuleVoltageProperties pvModuleVoltageProperties;
	@Before
	public void before(){
		PvModule pvPanel = new PvModule();
		pvPanel.setVmppSTC(32.1);
		pvPanel.setVocSTC(40.0);
		pvPanel.setTemperatureCoefficientOfVoc(0.29);
		pvModuleVoltageProperties = new PvModuleVoltageProperties(pvPanel);
	}

	@Test
	public void testCalculateVmppAtGivenTemperature_CaseHighTemperature() {
		double panelTemperature = 70.0;
		double voltage = pvModuleVoltageProperties.calculateVmppAtGivenTemperature(panelTemperature);
		assertEquals(26.88,voltage,0.01);
	}
	@Test
	public void testCalculateVmppAtGivenTemperature_CaseLowTemperature() {
		double panelTemperature = -25.0;
		double voltage = pvModuleVoltageProperties.calculateVmppAtGivenTemperature(panelTemperature);
		assertEquals(37.9,voltage,0.01);
	}
	
	@Test
	public void testCalculateOperatingVoltage(){
		double operatingVoltage = pvModuleVoltageProperties.calculateOperatingVoltage();
		assertEquals(29.2,operatingVoltage,0.01);
	}
	@Test
	public void testcalculateVocAtGivenTemperature(){
		double panelTemperature = -25.0;
		double voltage = pvModuleVoltageProperties.calculateVocAtGivenTemperature(panelTemperature);
		assertEquals(45.8,voltage,0.1);
	}
	

}
