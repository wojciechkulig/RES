package org.kulig.renewableenergy.service;


import java.util.List;

import org.kulig.renewableenergy.charts.CumulativeCashFlowChart;
import org.kulig.renewableenergy.economicAnalysis.EconomicIndicatorsCalculator;
import org.kulig.renewableenergy.model.DTO.EconomicAnalysisData;
import org.kulig.renewableenergy.model.entities.EconomicAnalysisResults;
import org.kulig.renewableenergy.model.entities.TariffGroup;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class EconomicAnalysisServiceImpl implements EconomicAnalysisService {
	
	@Autowired
	private ApplicationContext context;
	
	public String getTariffGroupName(){
		TariffGroup tariffGroup = context.getBean(TariffGroup.class);
		return tariffGroup.getName();
	}

	@Override
	public EconomicAnalysisData getEconomicAnalysisDataWithDefaultProperties() {
		return context.getBean(EconomicAnalysisData.class);
	
	}

	@Override
	public void updateEconomicAnalysisBean(EconomicAnalysisData data) {
		EconomicAnalysisData bean = context.getBean(EconomicAnalysisData.class);
		BeanUtils.copyProperties(data, bean);
		

	}

	@Override
	public EconomicAnalysisResults getEconomicAnalysisResults() {
		EconomicAnalysisResults results = new EconomicAnalysisResults();
		EconomicIndicatorsCalculator calculator = context.getBean(EconomicIndicatorsCalculator.class);
		results.setIRR(calculator.getIRR());
		results.setDiscountedCumulativeCashFlow(calculator.getCumulativeDiscountedCashFlow());
		results.setNPV(calculator.getNPV());
		results.setSPBT(calculator.getSPBP());
		results.setDPBP(calculator.getDPBP());
		results.setCumulativeCashFlowAfter15Years(calculator.getCumulativeCashFlowAfter15Years());
		return results;
	}
	public String getCumulativeCashFlowChartUrl(List<Double> cashFlow){
		return new CumulativeCashFlowChart().drawChart(cashFlow);
		
	}
	

}
