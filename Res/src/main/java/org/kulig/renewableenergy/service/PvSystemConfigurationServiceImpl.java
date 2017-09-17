package org.kulig.renewableenergy.service;

import java.util.Collection;
import java.util.List;

import org.kulig.renewableenergy.Energy.ContextClassOfAnnualEnergyBilanceStrategy;
import org.kulig.renewableenergy.charts.EnergyBilanceBarChart;
import org.kulig.renewableenergy.model.DTO.PvSystemConfigurationDTO;
import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.PvSystem;
import org.kulig.renewableenergy.model.entities.PvSystemEnergyBilance;
import org.kulig.renewableenergy.model.entities.TariffGroup;
import org.kulig.renewableenergy.repository.PvModuleRepository;
import org.kulig.renewableenergy.repository.TariffGroupRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;



@Service
public class PvSystemConfigurationServiceImpl implements PvSystemConfigurationService {
	@Autowired
	private TariffGroupRepository tariffGroupRepository;
	@Autowired
	private PvModuleRepository pvModuleRepository;
	
	@Autowired
	private ApplicationContext context;

	public Collection<TariffGroup> getTariffGroups(){
		return tariffGroupRepository.findAllOrderByTariffGroupName();
	}
	public Collection<PvModule> getPvModules(){
		return pvModuleRepository.findAll();
	}

	public void updatePvModuleBean(PvModule module){
		PvModule bean = context.getBean(PvModule.class);
		BeanUtils.copyProperties(module, bean);
	}
	public void updateTariffGroupBean(TariffGroup tariffGroup, double annualEnergyConsumption){
		tariffGroup.getHourlyEnergyConsumption().stream().forEach(e -> e.setHourly_Energy_Consumption(e.getHourly_Energy_Consumption()*annualEnergyConsumption/1000));
		TariffGroup bean = context.getBean(TariffGroup.class);
		BeanUtils.copyProperties(tariffGroup, bean);
	}
	@Override
	public List<List<PvSystemEnergyBilance>> calculateLifeTimeEnergyBilance(
			PvSystemConfigurationDTO systemConfigurationData) {
		PvSystem pvSystem = context.getBean(PvSystem.class);
		pvSystem.setNumberOfModules(systemConfigurationData.getNumberOfModules());
		ContextClassOfAnnualEnergyBilanceStrategy bilanceCalculator = context.getBean(ContextClassOfAnnualEnergyBilanceStrategy.class);
		return bilanceCalculator.calculateLifeTimeEnergyBalance(systemConfigurationData.getNumberOFSettlementPeriods(), getMethodOfSettlement(systemConfigurationData));
	}

	private String getMethodOfSettlement(PvSystemConfigurationDTO dto) {
		if(dto.getTariffGroup().getName().equals("G11")){
			return "G11";
		}
		if(dto.getTariffGroup().getName().equals("G12")){
			if(dto.getMethodOfSettlement().equals("Energa")){
				return "G12 Energa"; 
			}
			if(dto.getMethodOfSettlement().equals("Innogy")){
				return "G12 Innogy";
			}
			if(dto.getMethodOfSettlement().equals("PGE")){
				return "G12 PGE";
			}
			if(dto.getMethodOfSettlement().equals("Tauron")){
				return "G12 Tauron";
			}
		}
		if(dto.getTariffGroup().getName().equals("G12W")){
			if(dto.getMethodOfSettlement().equals("Energa")){
				return "G12W Energa";
			}
			if(dto.getMethodOfSettlement().equals("Innogy")){
				return "G12W Innogy";
			}
			if(dto.getMethodOfSettlement().equals("PGE")){
				return "G12W PGE";
			}
			if(dto.getMethodOfSettlement().equals("Tauron")){
				return "G12W Tauron";
			}
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public void updateEnergyBilance(List<List<PvSystemEnergyBilance>> bilance){		
		List<List<PvSystemEnergyBilance>> bean = (List<List<PvSystemEnergyBilance>>) context.getBean("bilance");
		bean.clear();
		bean.addAll(bilance);
	}
	public String createEnergyBilanceBarChart(List<List<PvSystemEnergyBilance>> bilance){
		return new EnergyBilanceBarChart().drawEnergyBilanceBarChart(bilance.get(0));
	}
	

}
