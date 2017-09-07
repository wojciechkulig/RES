package org.kulig.renewableenergy.economicAnalysis;

import java.util.List;

import org.kulig.renewableenergy.model.DTO.EconomicAnalysisDTO;
import org.kulig.renewableenergy.model.entities.PvSystemEnergyDistribution;

public class CashFlowCalculator {
	
	public CashFlowCalculator(EconomicAnalysisDTO economicAnalysisDTO){
		
	}
	/**
	 * cash flow w roku zero niech to beda koszty inwestycjyne
	 * mozliwe ze trzeba wywalic oplaty stale, bo i tak trzeba je oplacic czy sie inwestyuje czy ine
	 * @param energyDistributions
	 * @return
	 */
	public List<Double> calculateCashFlow(List<PvSystemEnergyDistribution> energyDistributions){
		
		return null;
	}

}
