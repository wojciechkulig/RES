package org.kulig.renewableenergy.economicAnalysis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EconomicIndicatorsCalculator {
	@Autowired
	private CashFlowCalculator cashFlow;
	@Autowired
	private NPVCalculator npvCalculator;
	
	public double getNPV(){
		return round(npvCalculator.calculateNPV(cashFlow.calculateYearlyCashFlow()),2);
	}
	public List<Double> getYearlyDiscountedCashFlow(){
		return npvCalculator.getYearlyDiscountedCashFlow(cashFlow.calculateYearlyCashFlow());
	}
	public List<Double> getYearlyCashFlow(){
		return cashFlow.calculateYearlyCashFlow();		
	}
	public double getIRR(){
		return new IRRCalculator().getIRR(cashFlow.calculateYearlyCashFlow());
	}
	public double getSPBP(){
		List<Double> cumulativeCashFlow = getCumulativeCashFlow();
		int lastNegative = 0;
		for(int i =0; i<cumulativeCashFlow.size();i++){
			if(cumulativeCashFlow.get(i) < 0){
				lastNegative = i;
			}
		}
		if(lastNegative +1 > cumulativeCashFlow.size() || cumulativeCashFlow.get(15) <0){
			return Double.NaN;
		}
		System.out.println("Cumulative w sbpt: " +cumulativeCashFlow.get(15));
		double SPBP = lastNegative + Math.abs(cumulativeCashFlow.get(lastNegative))/getYearlyCashFlow().get(lastNegative+1);
		return round(SPBP,2);
	}
	public double getDPBP(){
		List<Double> cumulativeDiscountedCashFlow = getCumulativeDiscountedCashFlow();
		int lastNegative = 0;
		for(int i =0; i<cumulativeDiscountedCashFlow.size();i++){
			if(cumulativeDiscountedCashFlow.get(i) < 0){
				lastNegative = i;
			}
		}
		if(lastNegative +1 > cumulativeDiscountedCashFlow.size()|| cumulativeDiscountedCashFlow.get(15) <0){
			return Double.NaN;
		}
		double DPBP = lastNegative + Math.abs(cumulativeDiscountedCashFlow.get(lastNegative))/getYearlyDiscountedCashFlow().get(lastNegative+1);
		return round(DPBP,2);
	}
	public double getCumulativeCashFlowAfter15Years(){
		return round(getCumulativeCashFlow().get(15),2);
	}
	
	
	public List<Double> getCumulativeCashFlow(){
		double init = 0;
		List<Double> cumulativeCashFlow = new ArrayList<>();
		for(Double cashflow:getYearlyCashFlow()){
			init += cashflow;
			cumulativeCashFlow.add(init);
		}
		return cumulativeCashFlow;
	}
	public List<Double> getCumulativeDiscountedCashFlow(){
		double init = 0;
		List<Double> cumulativeCashFlow = new ArrayList<>();
		for(Double cashflow:getYearlyDiscountedCashFlow()){
			init += cashflow;
			cumulativeCashFlow.add(init);
		}
		return cumulativeCashFlow;
	}
	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	

}
