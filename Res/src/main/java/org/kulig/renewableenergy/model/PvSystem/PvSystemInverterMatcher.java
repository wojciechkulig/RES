package org.kulig.renewableenergy.model.PvSystem;

import java.util.List;
import java.util.stream.Collectors;

import org.kulig.renewableenergy.model.PvSystem.PvModule.PvModuleVoltageProperties;
import org.kulig.renewableenergy.model.entities.Inverter;
import org.kulig.renewableenergy.model.entities.PvModule;

public class PvSystemInverterMatcher {
	private static final double OVERSIZE_COEFFICIENT = 1.2;
	private PvModule pvModule;
	private int numberOfPvModules;
	private boolean threePhasePowerSupply;
	private PvModuleVoltageProperties pvModuleVoltageProperties;

	public PvSystemInverterMatcher(PvModule pvModule, int numberOfPvModules, boolean threePhasePowerSupply) {
		this.pvModule = pvModule;
		this.numberOfPvModules = numberOfPvModules;
		this.threePhasePowerSupply = threePhasePowerSupply;
		this.pvModuleVoltageProperties = new PvModuleVoltageProperties(pvModule);
	}

	public List<Inverter> getMatchingInverters(List<Inverter> inverters) {
		return inverters.stream().filter(inverter -> isInverterMatching(inverter)).collect(Collectors.toList());
	}

	public boolean isInverterMatching(Inverter inverter) {

		return (isPhaseConditionMet(inverter) && isMaxPowerConditionMet(inverter) && isStringConditionMet(inverter));
	}

	private boolean isPhaseConditionMet(Inverter inverter) {
		if (inverter.isThreePhase() == threePhasePowerSupply) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isMaxPowerConditionMet(Inverter inverter) {
		if (calculateOversizedInputPower(inverter) >= pvModule.getNominalPower() * numberOfPvModules) {
			return true;
		} else {
			return false;
		}
	}

	private double calculateOversizedInputPower(Inverter inverter) {
		return inverter.getNominalPowerAC() * OVERSIZE_COEFFICIENT;
	}

	private boolean isStringConditionMet(Inverter inverter) {
		if (calculateNumberOfModulesInOneString(inverter)
				* calculateMaxNumberOfStrings(inverter) >= numberOfPvModules) {
			return true;
		} else {
			return false;
		}
	}

	public int calculateNumberOfModulesInOneString(Inverter inverter) {
		if (!isMinAmountOfModulesInOneString(inverter)) {
			return 0;
		} else {
			return calculateMaxAmountOfModulesInOneString(inverter);
		}
	}

	private boolean isMinAmountOfModulesInOneString(Inverter inverter) {
		if (calculateMinAmountOfModulesInOneString(inverter) < numberOfPvModules) {
			return true;
		} else {
			return false;
		}

	}

	public int calculateMinAmountOfModulesInOneString(Inverter inverter) {
		return (int) Math
				.ceil((inverter.getMinVoltageMPP() / pvModuleVoltageProperties.calculateVmppAtGivenTemperature(70)));
	}

	public int calculateMaxAmountOfModulesInOneString(Inverter inverter) {
		return Math.min(calculateMaxAmountOfModulesInOneStringVocCondition(inverter),
				calculateMaxAmountOfModulesInOneStringVmpptMaxCondition(inverter));
	}

	private int calculateMaxAmountOfModulesInOneStringVmpptMaxCondition(Inverter inverter) {
		return (int) Math
				.floor(inverter.getMaxVoltageMPP() / pvModuleVoltageProperties.calculateVmppAtGivenTemperature(-15));
	}

	private int calculateMaxAmountOfModulesInOneStringVocCondition(Inverter inverter) {
		return (int) Math
				.floor(inverter.getMaxInputVoltage() / pvModuleVoltageProperties.calculateVocAtGivenTemperature(-25));
	}

	public int calculateMaxNumberOfStrings(Inverter inverter) {
		int maxNumberOfStringsIscCondition = calculateMaxNumberOfStringsIscCondition(inverter);
		int NumberOfInverterDcInputs = inverter.getNumberOfDcInputs();
		if (NumberOfInverterDcInputs <= maxNumberOfStringsIscCondition) {
			return NumberOfInverterDcInputs;
		} else {
			return maxNumberOfStringsIscCondition;
		}
	}

	private int calculateMaxNumberOfStringsIscCondition(Inverter inverter) {
		return (int) Math.floor(inverter.getMaxInputCurrent() / pvModule.getIscSTC());
	}
	
	public PvModule getPvModule() {
		return pvModule;
	}

	public void setPvModule(PvModule pvModule) {
		this.pvModule = pvModule;
	}

	public int getNumberOfPvModules() {
		return numberOfPvModules;
	}

	public void setNumberOfPvModules(int numberOfPvModules) {
		this.numberOfPvModules = numberOfPvModules;
	}

	public boolean isThreePhasePowerSupply() {
		return threePhasePowerSupply;
	}

	public void setThreePhasePowerSupply(boolean threePhasePowerSupply) {
		this.threePhasePowerSupply = threePhasePowerSupply;
	}

	public PvModuleVoltageProperties getPvModuleVoltageProperties() {
		return pvModuleVoltageProperties;
	}

	public void setPvModuleVoltageProperties(PvModuleVoltageProperties pvModuleVoltageProperties) {
		this.pvModuleVoltageProperties = pvModuleVoltageProperties;
	}


}
