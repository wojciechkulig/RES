package org.kulig.renewableenergy.model.DTO;

public class EconomicAnalysisDTO {
	//cena energii
	private double electricityPriceDayTariff;
	private double electricityPriceNightTariff;
	private double fixedGridFee;
	private double variableGridFeeDay;
	private double variableGridFeeNight;
	private double qualityRateFee; //czy ona jest dzien/noc
	private double transitionalFee; // opłata przejsciowa
	private double RESFee;
	private double subscriptionFee;
	//wklad wlasny
	private double ownContribution; // procentowo czy kwota
	private double riskPremium;
	private double referenceRate; //oprocentowanie lokaty
	//oprocentowanie kredytu
	private double interestRate;
	private double creditDuration;
	private double annualInstalmentPeriods;
	private double creditAmount;
	private boolean isInstallmentFixed;
	
	//bezzwrotny udzial
	private double subsidy;
	
	//instalacja pv
	private double operatingCosts;
		

	private double initialInvestmentCost; //io
	private double annualIncreaseOfElectricityPrice;
	private double inflation;

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
	public double getQualityRateFee() {
		return qualityRateFee;
	}
	public void setQualityRateFee(double qualityRateFee) {
		this.qualityRateFee = qualityRateFee;
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
	public double getSubscriptionFee() {
		return subscriptionFee;
	}
	public void setSubscriptionFee(double subscriptionFee) {
		this.subscriptionFee = subscriptionFee;
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
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public double getCreditDuration() {
		return creditDuration;
	}
	public void setCreditDuration(double creditDuration) {
		this.creditDuration = creditDuration;
	}
	public double getAnnualInstalmentPeriods() {
		return annualInstalmentPeriods;
	}
	public void setAnnualInstalmentPeriods(double annualInstalmentPeriods) {
		this.annualInstalmentPeriods = annualInstalmentPeriods;
	}
	public double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}
	public boolean isInstallmentFixed() {
		return isInstallmentFixed;
	}
	public void setInstallmentFixed(boolean isInstallmentFixed) {
		this.isInstallmentFixed = isInstallmentFixed;
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
	public double getInitialInvestmentCost() {
		return initialInvestmentCost;
	}
	public void setInitialInvestmentCost(double initialInvestmentCost) {
		this.initialInvestmentCost = initialInvestmentCost;
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
	
	

}
