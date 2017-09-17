package org.kulig.renewableenergy.economicAnalysis;

import java.util.ArrayList;
import java.util.List;

public class InstallmentCalculator {
	private double interestRate;
	private int creditDuration;
	private double annualInstalmentPeriods;
	private double creditAmount;
	private String installmentType;

	public InstallmentCalculator(Credit credit) {
		this.interestRate = credit.getInterestRate()/100;
		this.creditDuration = credit.getCreditDuration();
		this.annualInstalmentPeriods = credit.getAnnualInstalmentPeriods();
		this.creditAmount = credit.getCreditAmount();
		this.installmentType = credit.getInstallmentType();
	}

	public List<Double> getAnnualCreditInstallments() {
		if (installmentType.equals("fixed")) {
			return getAnnualFixedCreditInstallments();
		} else {
			return getAnnualDecreasingCreditInstallments();
		}
	}
	/**
	 * S * q^n * (q-1)(q^n -1)
	 * S - credit amount
	 * q =  1 + (interestRate/annualIntalmentPeriods)
	 * n - total amount of installments
	 * http://www.destro.pl/bankowosc/jaki-jest-wzor-na-rate-kredytu/
	 * @return
	 */
	private List<Double> getAnnualFixedCreditInstallments() {
		List<Double> annualFixedCreditInstallments = new ArrayList<>();
		double q =  1 + (interestRate/annualInstalmentPeriods);
		double n = creditDuration*annualInstalmentPeriods;
		double annualInstallments = 12*creditAmount*Math.pow(q, n)*(q-1)*(Math.pow(q, n) -1);
		for(int i = 0; i <creditDuration; i++){
			annualFixedCreditInstallments.add(annualInstallments);
		}
		addElementsToListUpTo15Elements(annualFixedCreditInstallments);
		//add zero for this arraylist so it can has the same 
		return annualFixedCreditInstallments;
	}

	private List<Double> getAnnualDecreasingCreditInstallments() {
		double remainingCreditAmount = creditAmount;
		List<Double> annualDecreasingCreditInstallments = new ArrayList<>();
		for (int i = 0; i < creditDuration; i++) {
			double capitalPart = creditAmount / creditDuration;
			double interestPart = getInterestPart(remainingCreditAmount);
			remainingCreditAmount -= capitalPart;
			annualDecreasingCreditInstallments.add(capitalPart + interestPart);
		}
		addElementsToListUpTo15Elements(annualDecreasingCreditInstallments);
		//add zero for this arraylist so it can iterare
		return annualDecreasingCreditInstallments;
	}

	private double getInterestPart(double remainingCreditAmount) {
		double interestPart = 0;
		for (int i = 0; i < annualInstalmentPeriods; i++) {
			interestPart += remainingCreditAmount * interestRate / annualInstalmentPeriods;
			remainingCreditAmount -= creditAmount / creditDuration * annualInstalmentPeriods;
		}
		return interestPart;
	}
	/**
	 * This method returns 15 element array. It is required to iterate over pv instalation lifetime that is 15 years without exception;
	 * @param list
	 * @return
	 */
	private void addElementsToListUpTo15Elements(List<Double> list){
		for(int i = creditDuration; i<15; i++){
			list.add(0.0);
		}
	}

}
