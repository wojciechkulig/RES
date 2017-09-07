package org.kulig.renewableenergy.charts;

import java.util.List;
import java.util.stream.Collectors;

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

public class IrradianceBarChart {

	
	public String drawMonthlyIrradianceBarChart(List<Double> irradiance){
		irradiance = formatIrradianceMonthlyValuesIntoPercentages(irradiance);
	    BarChartPlot barChartPlot = Plots.newBarChartPlot(Data.newData(irradiance), Color.AQUAMARINE);
	    // Instantiating chart.
	    BarChart chart = GCharts.newBarChart(barChartPlot);

	    // Defining axis info and styles
	    AxisStyle axisStyle = AxisStyle.newAxisStyle(Color.BLACK, 13, AxisTextAlignment.CENTER);
	    AxisLabels y = AxisLabelsFactory.newAxisLabels("      [%]", 50.0);
	    y.setAxisStyle(axisStyle);
	    AxisLabels x = AxisLabelsFactory.newAxisLabels("Miesiąc", 50.0);
	    x.setAxisStyle(axisStyle);

	    // Adding axis info to chart.
	    chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("01", "02", "03", "04","05","06","07","08","09"
	    		,"10","11","12"));
	    chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, 25));
	    chart.addYAxisLabels(y);
	    chart.addXAxisLabels(x);

	    chart.setSize(600, 400);
	    chart.setBarWidth(20);
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

	private List<Double> formatIrradianceMonthlyValuesIntoPercentages(List<Double> irradiance) {
		Double yearlyIrradiance = irradiance.stream().mapToDouble(Double::doubleValue).sum();
		return irradiance.stream().map(i->100*(i*100/yearlyIrradiance)/25).collect(Collectors.toList());		
	}

}
