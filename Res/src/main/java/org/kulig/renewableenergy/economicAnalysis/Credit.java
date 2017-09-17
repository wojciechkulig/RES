package org.kulig.renewableenergy.economicAnalysis;

public class Credit {
	private double interestRate;
	private int creditDuration;
	private int annualInstalmentPeriods;
	private double creditAmount;
	private String installmentType;
	
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getCreditDuration() {
		return creditDuration;
	}
	public void setCreditDuration(int creditDuration) {
		this.creditDuration = creditDuration;
	}
	
	public int getAnnualInstalmentPeriods() {
		return annualInstalmentPeriods;
	}
	public void setAnnualInstalmentPeriods(int annualInstalmentPeriods) {
		this.annualInstalmentPeriods = annualInstalmentPeriods;
	}
	public double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getInstallmentType() {
		return installmentType;
	}
	public void setInstallmentType(String installmentType) {
		this.installmentType = installmentType;
	}

	
}
