package org.kulig.renewableenergy.service;

import java.util.List;

import org.kulig.renewableenergy.model.DTO.EconomicAnalysisData;
import org.kulig.renewableenergy.model.entities.EconomicAnalysisResults;

public interface EconomicAnalysisService {
	public String getTariffGroupName();
	public EconomicAnalysisData getEconomicAnalysisDataWithDefaultProperties();
	public void updateEconomicAnalysisBean(EconomicAnalysisData bean);
	public EconomicAnalysisResults getEconomicAnalysisResults();
	public String getCumulativeCashFlowChartUrl(List<Double> cashFlow);

}
