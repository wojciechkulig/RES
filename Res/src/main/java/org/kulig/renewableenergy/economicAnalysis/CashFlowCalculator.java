package org.kulig.renewableenergy.economicAnalysis;

import java.util.ArrayList;
import java.util.List;

import org.kulig.renewableenergy.model.DTO.EconomicAnalysisData;
import org.kulig.renewableenergy.model.entities.PvSystemEnergyBilance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CashFlowCalculator {

	@Autowired
	private List<List<PvSystemEnergyBilance>> energyDistributions;
	@Autowired
	private EconomicAnalysisData economicAnalysisData;

	private List<Double> installments;

	public List<Double> calculateYearlyCashFlow() {
		installments = new InstallmentCalculator(economicAnalysisData.getCredit()).getAnnualCreditInstallments();
		List<Double> cashFlow = new ArrayList<>();
		cashFlow.add(-economicAnalysisData.getOwnContribution());
		for (int year = 0; year < 15; year++) {
			cashFlow.add(calculateCashFlow(year));
		}

		return cashFlow;
	}

	private double calculateCashFlow(int year) {
		double cashFlow = 0;
		double energyPriceDay = (economicAnalysisData.getElectricityPriceDayTariff()
				+ economicAnalysisData.getVariableGridFeeDay() + economicAnalysisData.getRESFee() + economicAnalysisData.getQualityRateFeeDay())*Math.pow(1+(economicAnalysisData.getAnnualIncreaseOfElectricityPrice()/100), year+1);
		double energyPriceNight =(economicAnalysisData.getElectricityPriceNightTariff()
				+ economicAnalysisData.getVariableGridFeeNight() + economicAnalysisData.getRESFee() + economicAnalysisData.getQualityRateFeeNight())*Math.pow(1+(economicAnalysisData.getAnnualIncreaseOfElectricityPrice()/100), year+1);
		double annualEnergyConsumedNightTariff = energyDistributions.get(year).stream()
				.mapToDouble(d -> d.getPeriodEnergyConsumptionNight()).sum();
		double annualEnergyPurchasedNightTariff = energyDistributions.get(year).stream()
				.mapToDouble(d -> d.getPeriodEnergyPurchasedNight()).sum();
		double annualEnergyConsumedDayTariff = energyDistributions.get(year).stream()
				.mapToDouble(d -> d.getPeriodEnergyConsumptionDay()).sum();
		double annualEnergyPurchasedDayTariff = energyDistributions.get(year).stream()
				.mapToDouble(d -> d.getPeriodEnergyPurchasedDay()).sum();
		cashFlow = (annualEnergyConsumedNightTariff - annualEnergyPurchasedNightTariff) * energyPriceNight
				+ (annualEnergyConsumedDayTariff - annualEnergyPurchasedDayTariff) * energyPriceDay
				- installments.get(year) - economicAnalysisData.getOperatingCosts();
		return cashFlow;
	}

}
