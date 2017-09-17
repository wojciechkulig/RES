package org.kulig.renewableenergy.economicAnalysis;

import java.util.ArrayList;
import java.util.List;

import org.kulig.renewableenergy.model.DTO.EconomicAnalysisData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NPVCalculator {
	@Autowired
	private EconomicAnalysisData data;
	
	public double calculateNPV(List<Double> yearlyCashFlow){
		List<Double> yearlyDiscountedCashFlow = getYearlyDiscountedCashFlow(yearlyCashFlow);
		return yearlyDiscountedCashFlow.stream().mapToDouble(d->d).sum();
	}
	public List<Double> getYearlyDiscountedCashFlow(List<Double> yearlyCashFlow){
		List<Double> yearlyDiscountedCashFlow = new ArrayList<>();
		double r = calculateDiscountRate();
		yearlyDiscountedCashFlow.add(yearlyCashFlow.get(0));
		for(int year =1; year<=15; year++){
			double discountedCashFlow = yearlyCashFlow.get(year)/Math.pow(1+ r, year);
			yearlyDiscountedCashFlow.add(discountedCashFlow);
		}
		return yearlyDiscountedCashFlow;
	}

	private double calculateDiscountRate() {
		double initialInvestmentCost = data.getCredit().getCreditAmount() + data.getOwnContribution() + data.getSubsidy();
		double creditDiscountRate = (data.getCredit().getCreditAmount()/ initialInvestmentCost)*(data.getCredit().getInterestRate()/100);
		double ownContributionDiscountRate = (data.getOwnContribution() / initialInvestmentCost)*((data.getRiskPremium()+ data.getReferenceRate())/100);
		double discountRate = ((creditDiscountRate + ownContributionDiscountRate) - (data.getInflation()/100))/(1+(data.getInflation()/100));
		System.out.println("Discount rate:"+ discountRate);
		return discountRate;
	}
}
