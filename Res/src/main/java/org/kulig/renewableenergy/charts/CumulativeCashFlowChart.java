package org.kulig.renewableenergy.charts;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.LinearGradientFill;
import com.googlecode.charts4j.Plot;
import com.googlecode.charts4j.Plots;

public class CumulativeCashFlowChart {
	public String drawChart(List<Double> cumulativeCashFlow){
		double minValue = cumulativeCashFlow.stream().min((e1,e2)->(int)(e1-e2)).orElse(0.0);
		double maxValue = cumulativeCashFlow.stream().max((e1,e2)->(int)(e1-e2)).orElse(0.0);
		cumulativeCashFlow = formatData(cumulativeCashFlow);
		
		Plot plot = Plots.newPlot(Data.newData(cumulativeCashFlow));
	    LineChart chart = GCharts.newLineChart(plot);
	    AxisStyle axisStyle = AxisStyle.newAxisStyle(Color.BLACK, 13, AxisTextAlignment.CENTER);
	    AxisLabels y = AxisLabelsFactory.newAxisLabels("zł", 50.0);
	    y.setAxisStyle(axisStyle);
	    AxisLabels x = AxisLabelsFactory.newAxisLabels("rok", 50.0);
	    x.setAxisStyle(axisStyle);
	    
	    // Adding axis info to chart.
	    List<String> nazwy = new ArrayList<>();
	    for(int i=0; i<16;i++){
	    	nazwy.add(""+i);
	    }

	    chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(nazwy));
	    chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(minValue, maxValue));
	    chart.addYAxisLabels(y);
	    chart.addXAxisLabels(x);
	    chart.setSize(650, 450);
	    chart.setGrid(100, 4, 3, 5);
	    chart.setBackgroundFill(Fills.newSolidFill(Color.WHITE));
	    LinearGradientFill fill = Fills.newLinearGradientFill(0, Color.LAVENDER, 100);
	    fill.addColorAndOffset(Color.WHITE, 0);
	    chart.setAreaFill(fill);
	    String url = chart.toURLString();
		return url;
	}

	private List<Double> formatData(List<Double> cumulativeCashFlow) {
		double minValue = cumulativeCashFlow.stream().min((e1,e2)->(int)(e1-e2)).orElse(0.0);
		double maxValue = cumulativeCashFlow.stream().max((e1,e2)->(int)(e1-e2)).orElse(0.0);
		List<Double> formattedCashFlow = new ArrayList<>();
		if(minValue >=0){
			for(Double cash: cumulativeCashFlow){
				formattedCashFlow.add(100*cash/maxValue);
			}
			return formattedCashFlow;
		}
		maxValue = maxValue-minValue;
		for(Double cashFlow:cumulativeCashFlow){
			formattedCashFlow.add((cashFlow -minValue)*100/maxValue);
		}
		return formattedCashFlow;
	}


}
