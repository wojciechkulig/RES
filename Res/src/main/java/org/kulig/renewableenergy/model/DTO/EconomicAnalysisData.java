package org.kulig.renewableenergy.model.DTO;

import org.kulig.renewableenergy.economicAnalysis.Credit;
import org.springframework.stereotype.Component;

@Component
public class EconomicAnalysisData {
	//cena energii
	private double electricityPriceDayTariff;
	private double electricityPriceNightTariff;
	private double fixedGridFee;
	private double variableGridFeeDay;
	private double variableGridFeeNight;
	private double qualityRateFeeDay;
	private double qualityRateFeeNight;
	private double transitionalFee; // opłata przejsciowa
	private double RESFee = 0.02;
	//wklad wlasny
	private double ownContribution; // kwota
	private double riskPremium;
	private double referenceRate =1.5; //oprocentowanie lokaty
	//oprocentowanie kredytu
	private Credit credit;
	
	private double totalBudget;
	
	//bezzwrotny udzial
	private double subsidy;
	
	//instalacja pv
	private double operatingCosts;
		
	private double annualIncreaseOfElectricityPrice =4.7;
	private double inflation = 2.13;

	public double getElectricityPriceDayTariff() {
		return electricityPriceDayTariff;
	}
	public void setElectricityPriceDayTariff(double electricityPriceDayTariff) {
		this.electricityPriceDayTariff = electricityPriceDayTariff;
	}
	public double getElectricityPriceNightTariff() {
		return electricityPriceNightTariff;
	}
	public void setElectricityPriceNightTariff(double electricityPriceNightTariff) {
		this.electricityPriceNightTariff = electricityPriceNightTariff;
	}
	public double getFixedGridFee() {
		return fixedGridFee;
	}
	public void setFixedGridFee(double fixedGridFee) {
		this.fixedGridFee = fixedGridFee;
	}
	public double getVariableGridFeeDay() {
		return variableGridFeeDay;
	}
	public void setVariableGridFeeDay(double variableGridFeeDay) {
		this.variableGridFeeDay = variableGridFeeDay;
	}
	public double getVariableGridFeeNight() {
		return variableGridFeeNight;
	}
	public void setVariableGridFeeNight(double variableGridFeeNight) {
		this.variableGridFeeNight = variableGridFeeNight;
	}

	public double getTransitionalFee() {
		return transitionalFee;
	}
	public void setTransitionalFee(double transitionalFee) {
		this.transitionalFee = transitionalFee;
	}
	public double getRESFee() {
		return RESFee;
	}
	public void setRESFee(double rESFee) {
		RESFee = rESFee;
	}
	public double getOwnContribution() {
		return ownContribution;
	}
	public void setOwnContribution(double ownContribution) {
		this.ownContribution = ownContribution;
	}
	public double getRiskPremium() {
		return riskPremium;
	}
	public void setRiskPremium(double riskPremium) {
		this.riskPremium = riskPremium;
	}
	public double getReferenceRate() {
		return referenceRate;
	}
	public void setReferenceRate(double referenceRate) {
		this.referenceRate = referenceRate;
	}
	
	public Credit getCredit() {
		return credit;
	}
	public void setCredit(Credit credit) {
		this.credit = credit;
	}
	public double getSubsidy() {
		return subsidy;
	}
	public void setSubsidy(double subsidy) {
		this.subsidy = subsidy;
	}
	public double getOperatingCosts() {
		return operatingCosts;
	}
	public void setOperatingCosts(double operatingCosts) {
		this.operatingCosts = operatingCosts;
	}
	public double getAnnualIncreaseOfElectricityPrice() {
		return annualIncreaseOfElectricityPrice;
	}
	public void setAnnualIncreaseOfElectricityPrice(double annualIncreaseOfElectricityPrice) {
		this.annualIncreaseOfElectricityPrice = annualIncreaseOfElectricityPrice;
	}
	public double getInflation() {
		return inflation;
	}
	public void setInflation(double inflation) {
		this.inflation = inflation;
	}
	public double getQualityRateFeeDay() {
		return qualityRateFeeDay;
	}
	public void setQualityRateFeeDay(double qualityRateFeeDay) {
		this.qualityRateFeeDay = qualityRateFeeDay;
	}
	public double getQualityRateFeeNight() {
		return qualityRateFeeNight;
	}
	public void setQualityRateFeeNight(double qualityRateFeeNight) {
		this.qualityRateFeeNight = qualityRateFeeNight;
	}
	public double getTotalBudget() {
		return totalBudget;
	}
	public void setTotalBudget(double totalBudget) {
		this.totalBudget = totalBudget;
	}
	
	
}
