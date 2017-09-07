package org.kulig.renewableenergy.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.kulig.renewableenergy.Energy.EnergyDistributionCalculator;
import org.kulig.renewableenergy.model.entities.City;
import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.PvSystem;
import org.kulig.renewableenergy.model.entities.PvSystemEnergyDistribution;
import org.kulig.renewableenergy.model.entities.TariffGroup;
import org.kulig.renewableenergy.model.entities.Weather;
import org.kulig.renewableenergy.repository.PvModuleRepository;
import org.kulig.renewableenergy.repository.TariffGroupRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;



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
	public void calculate(){
		PvSystem pvSystem = context.getBean(PvSystem.class);
		pvSystem.setNumberOfModules(1);
		EnergyDistributionCalculator energyDistributionCalculator = context.getBean(EnergyDistributionCalculator.class);
		List<PvSystemEnergyDistribution> yearlyEnergyDistribution = energyDistributionCalculator.calculateLifeTimeEnergyBalance(1);
		List<Double> pogoda = yearlyEnergyDistribution.get(0).getHourlyEnergyYield();
		List<Double> konsumpcja = yearlyEnergyDistribution.get(0).getHourlyEnergyConsumption();
		zapiszPogode(pogoda,konsumpcja);
		//zapiszDoExcela(yearlyEnergyDistribution);
		
		
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
	private void zapiszPogode(List<Double> energia, List<Double> konsumpcja){
        //1. Create an Excel file
        WritableWorkbook myFirstWbook = null;
        try {
        	String EXCEL_FILE_LOCATION = "C:\\Users\\Wojtek\\Desktop\\przyklad.xls";
            myFirstWbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));

            // create an Excel sheet
            WritableSheet excelSheet = myFirstWbook.createSheet("Sheet 1", 0);

            // add something into the Excel sheet
            Label label = new Label(0, 0, "energia");
            excelSheet.addCell(label);
            label = new Label(1,0,"konsumpcja");
            excelSheet.addCell(label);
            for(int i = 0; i <8760; i++){
            	Number number = new Number(0,i,i+1);
            	excelSheet.addCell(number);
            	number = new Number(1,i,energia.get(i));
            	excelSheet.addCell(number);
            	number = new Number(2,i,konsumpcja.get(i));
            	excelSheet.addCell(number);
            }

            myFirstWbook.write();
            System.out.println("koniec");
            

        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {

            if (myFirstWbook != null) {
                try {
                    myFirstWbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }


        }
	}
	private void zapiszDoExcela(List<PvSystemEnergyDistribution> lista){
        //1. Create an Excel file
        WritableWorkbook myFirstWbook = null;
        try {
        	String EXCEL_FILE_LOCATION = "C:\\Users\\Wojtek\\Desktop\\przyklad.xls";
            myFirstWbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));

            // create an Excel sheet
            WritableSheet excelSheet = myFirstWbook.createSheet("Sheet 1", 0);

            // add something into the Excel sheet
            Label label = new Label(0, 0, "rok");
            excelSheet.addCell(label);
            label = new Label(1,0,"annual energy yield");
            excelSheet.addCell(label);
            label = new Label(2,0,"annual energy auto consumption");
            excelSheet.addCell(label);
            label = new Label(3,0,"annual energy annualEnergyTakenFromGrid");
            excelSheet.addCell(label);
            label = new Label(4,0,"annual energy consumption");
            excelSheet.addCell(label);
            for(int i = 0; i <15; i++){
            	Number number = new Number(0,i,i+1);
            	excelSheet.addCell(number);
            	number = new Number(1,i,lista.get(i).getAnnualEnergyYield());
            	excelSheet.addCell(number);
            	number = new Number(2,i,lista.get(i).getAnnualEnergyAutoConsumption());
            	excelSheet.addCell(number);
            	number = new Number(3,i,lista.get(i).getAnnualEnergyTakenFromGrid());
            	excelSheet.addCell(number);
            	number = new Number(4,i,lista.get(i).getAnnualEnergyConsumption());
            	excelSheet.addCell(number);
            }

            myFirstWbook.write();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {

            if (myFirstWbook != null) {
                try {
                    myFirstWbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }


        }
	}
	

}
