package org.kulig.renewableenergy.economicAnalysis;

import java.util.ArrayList;
import java.util.List;

public class InstallmentCalculator {
	private double interestRate;
	private double creditDuration;
	private double annualInstalmentPeriods;
	private double creditAmount;
	private boolean isInstallmentFixed;

	public InstallmentCalculator(Credit credit) {
		this.interestRate = credit.getInterestRate();
		this.creditDuration = credit.getCreditDuration();
		this.annualInstalmentPeriods = credit.getAnnualInstalmentPeriods();
		this.creditAmount = credit.getCreditAmount();
		this.isInstallmentFixed = credit.isInstallmentFixed();
	}

	public List<Double> getAnnualCreditInstallments() {
		if (isInstallmentFixed) {
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
		return annualFixedCreditInstallments;
	}

	private List<Double> getAnnualDecreasingCreditInstallments() {
		double remainingCreditAmount = creditAmount;
		List<Double> AnnualFixedCreditInstallments = new ArrayList<>();
		for (int i = 0; i < creditDuration; i++) {
			double capitalPart = creditAmount / creditDuration;
			double interestPart = getInterestPart(remainingCreditAmount);
			remainingCreditAmount -= capitalPart;
			AnnualFixedCreditInstallments.add(capitalPart + interestPart);
		}
		return AnnualFixedCreditInstallments;
	}

	private double getInterestPart(double remainingCreditAmount) {
		double interestPart = 0;
		for (int i = 0; i < annualInstalmentPeriods; i++) {
			interestPart += remainingCreditAmount * interestRate / annualInstalmentPeriods;
			remainingCreditAmount -= creditAmount / creditDuration * annualInstalmentPeriods;
		}
		return interestPart;
	}

}
