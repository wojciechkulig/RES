package org.kulig.renewableenergy.charts;

import java.util.ArrayList;
import java.util.List;

import org.kulig.renewableenergy.model.entities.PvSystemEnergyBilance;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LinearGradientFill;
import com.googlecode.charts4j.Plots;

public class EnergyBilanceBarChart {
	public String drawEnergyBilanceBarChart(List<PvSystemEnergyBilance> list){
		PvSystemEnergyBilance max = list.stream().max((e1, e2)->(int)(e1.getPeriodEnergyConsumptionDay()+e1.getPeriodEnergyConsumptionNight()- e2.getPeriodEnergyConsumptionDay()-e2.getPeriodEnergyConsumptionNight())).orElse(null);
		double maxValue = max.getPeriodEnergyConsumptionDay()+max.getPeriodEnergyConsumptionNight();
		if(list.get(0).getPeriodEnergyConsumptionNight() ==0){
			return drawG11EnergyBilanceBarChart(list,maxValue);
		}else{
			return drawG12EnergyBilanceBarChart(list,maxValue);
		}
	}

	private String drawG12EnergyBilanceBarChart(List<PvSystemEnergyBilance> list, double maxValue) {
		
		BarChartPlot Day = Plots.newBarChartPlot(Data.newData(getDayData(list,maxValue)), Color.YELLOW, "Taryfa dzienna");
		BarChartPlot Night = Plots.newBarChartPlot(Data.newData(getNightData(list,maxValue)), Color.BLACK, "Taryfa nocna");
		
		BarChart chart = GCharts.newBarChart(Day,Night);
		
	    // Defining axis info and styles
	    AxisStyle axisStyle = AxisStyle.newAxisStyle(Color.BLACK, 13, AxisTextAlignment.CENTER);
	    AxisLabels y = AxisLabelsFactory.newAxisLabels("      [kwh]", 50.0);
	    y.setAxisStyle(axisStyle);
	    AxisLabels x = AxisLabelsFactory.newAxisLabels("okres rozliczeniowy  numer- numer okresu rozliczeniwego, z->zużyto, k->kupiono", 50.0);
	    x.setAxisStyle(axisStyle);
	    
	    // Adding axis info to chart.
	    List<String> nazwy = new ArrayList<>();
	    for(int i = 0; i <list.size();i++){
	    	nazwy.add(i+1 + "z");
	    	nazwy.add(i+1 + "k");
	    }
	    chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(nazwy));
	    chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, maxValue));
	    chart.addYAxisLabels(y);
	    chart.addXAxisLabels(x);
	    chart.setSize(650, 450);
	    chart.setBarWidth(BarChart.AUTO_RESIZE);
	    chart.setSpaceWithinGroupsOfBars(20);
	    chart.setDataStacked(true);
	    chart.setGrid(100, 4, 3, 5);
	    chart.setBackgroundFill(Fills.newSolidFill(Color.WHITE));
	    LinearGradientFill fill = Fills.newLinearGradientFill(0, Color.LAVENDER, 100);
	    fill.addColorAndOffset(Color.WHITE, 0);
	    chart.setAreaFill(fill);
	    String url = chart.toURLString();
		return url;
	}

	private String drawG11EnergyBilanceBarChart(List<PvSystemEnergyBilance> list, double maxValue) {
		BarChartPlot Day = Plots.newBarChartPlot(Data.newData(getDayData(list,maxValue)), Color.YELLOW, "Taryfa dzienna");
		BarChart chart = GCharts.newBarChart(Day);
	    AxisStyle axisStyle = AxisStyle.newAxisStyle(Color.BLACK, 13, AxisTextAlignment.CENTER);
	    AxisLabels y = AxisLabelsFactory.newAxisLabels("      [kwh]", 50.0);
	    y.setAxisStyle(axisStyle);
	    AxisLabels x = AxisLabelsFactory.newAxisLabels("okres rozliczeniowy  numer-> numer okresu rozliczeniwego, z->zużyto, k->kupiono", 50.0);
	    x.setAxisStyle(axisStyle);
	    // Adding axis info to chart.
	    List<String> nazwy = new ArrayList<>();
	    for(int i = 0; i <list.size();i++){
	    	nazwy.add(i+1 + "z");
	    	nazwy.add(i+1 + "k");
	    }
	    chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(nazwy));
	    chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, maxValue));
	    chart.addYAxisLabels(y);
	    chart.addXAxisLabels(x);
	    chart.setSize(650, 450);
	    chart.setBarWidth(BarChart.AUTO_RESIZE);
	    chart.setSpaceWithinGroupsOfBars(20);
	    chart.setDataStacked(true);
	    chart.setGrid(100, 4, 3, 5);
	    chart.setBackgroundFill(Fills.newSolidFill(Color.WHITE));
	    LinearGradientFill fill = Fills.newLinearGradientFill(0, Color.LAVENDER, 100);
	    fill.addColorAndOffset(Color.WHITE, 0);
	    chart.setAreaFill(fill);
	    String url = chart.toURLString();
		return url;
	}
	private List<Double> getDayData(List<PvSystemEnergyBilance> list, double maxValue){		
		List<Double> dayData = new ArrayList<>();
		for(PvSystemEnergyBilance dist: list){
			dayData.add((100 * dist.getPeriodEnergyConsumptionDay())/maxValue);
			dayData.add((100 *dist.getPeriodEnergyPurchasedDay())/maxValue);
		}
		return dayData;
	}
	private List<Double> getNightData(List<PvSystemEnergyBilance> list, double maxValue){
		List<Double> dayData = new ArrayList<>();
		for(PvSystemEnergyBilance dist: list){
			dayData.add((100* dist.getPeriodEnergyConsumptionNight())/maxValue);
			dayData.add((100 * dist.getPeriodEnergyPurchasedNight())/maxValue);
		}
		return dayData;
	}

}
