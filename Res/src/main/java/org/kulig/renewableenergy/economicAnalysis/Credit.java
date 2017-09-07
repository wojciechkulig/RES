package org.kulig.renewableenergy.economicAnalysis;

public class Credit {
	private double interestRate;
	private double creditDuration;
	private double annualInstalmentPeriods;
	private double creditAmount;
	private boolean isInstallmentFixed;
	
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
	
}
