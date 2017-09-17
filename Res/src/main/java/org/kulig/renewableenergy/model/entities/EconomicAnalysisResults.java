package org.kulig.renewableenergy.model.entities;

import java.util.List;

public class EconomicAnalysisResults {
	private double IRR;
	private double NPV;
	private double SPBT;
	private double DPBP;
	private double cumulativeCashFlowAfter15Years;
	private List<Double> discountedCumulativeCashFlow;

	public double getIRR() {
		return IRR;
	}
	public void setIRR(double iRR) {
		IRR = iRR;
	}
	public double getNPV() {
		return NPV;
	}
	public void setNPV(double nPV) {
		NPV = nPV;
	}
	public List<Double> getDiscountedCumulativeCashFlow() {
		return discountedCumulativeCashFlow;
	}
	public void setDiscountedCumulativeCashFlow(List<Double> discountedCumulativeCashFlow) {
		this.discountedCumulativeCashFlow = discountedCumulativeCashFlow;
	}
	public double getSPBT() {
		return SPBT;
	}
	public void setSPBT(double sPBT) {
		SPBT = sPBT;
	}
	public double getDPBP() {
		return DPBP;
	}
	public void setDPBP(double dPBP) {
		DPBP = dPBP;
	}
	public double getCumulativeCashFlowAfter15Years() {
		return cumulativeCashFlowAfter15Years;
	}
	public void setCumulativeCashFlowAfter15Years(double cumulativeCashFlowAfter15Years) {
		this.cumulativeCashFlowAfter15Years = cumulativeCashFlowAfter15Years;
	}
	
	
	

	

}
