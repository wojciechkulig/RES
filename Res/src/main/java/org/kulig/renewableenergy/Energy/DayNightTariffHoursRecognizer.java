package org.kulig.renewableenergy.Energy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.kulig.renewableenergy.model.entities.Weather;


public class DayNightTariffHoursRecognizer {
	private List<Weather> weather;
	private String tariffGroup;
		
	public DayNightTariffHoursRecognizer(List<Weather> weather, String tariffGroup){
		this.weather = weather;
		this.tariffGroup = tariffGroup;
	}
	public List<Integer> getNightTariffHoursForPeriod(int startMonth, int endMonth){
		if(tariffGroup.equals("G11")){
			return new ArrayList<Integer>();
		}
		if(tariffGroup.equals("G12")){
			return getG12NightTariffHoursForPeriod(startMonth,endMonth);
		}
		return getG12wNightTariffHoursForPeriod(startMonth, endMonth);
	}
	public List<Integer> getDayTariffHoursForPeriod(int startMonth, int endMonth){
		if(tariffGroup.equals("G11")){
			return getG11HoursForPeriod(startMonth, endMonth);
		}
		if(tariffGroup.equals("G12")){
			return getG12DayTariffHoursForPeriod(startMonth, endMonth);
		}
		return getG12wDayTariffHoursForPeriod(startMonth, endMonth);
	}

	public List<Integer> getG12NightTariffHoursForPeriod(int startMonth, int endMonth){
		int start = startMonth;
		int end = endMonth;
		List<Integer> nightTariffHours= Arrays.asList(0,1,2,3,4,5,13,14,22,23);
		return weather.stream().filter(e-> e.getM()>=start && e.getM()<=end &&nightTariffHours.contains(e.getUTC_H()%24)).map(e -> e.getH()-1).collect(Collectors.toList());
	}
	
	public List<Integer> getG11HoursForPeriod(int startMonth, int endMonth){
		int start = startMonth;
		int end = endMonth;
		return weather.stream().filter(e-> e.getM()>=start && e.getM()<=end).map(e-> e.getH()-1).collect(Collectors.toList());
	}
	
	public List<Integer> getG12wNightTariffHoursForPeriod(int startMonth, int endMonth){
		int start = startMonth;
		int end = endMonth;
		List<Integer> nightTariffHours= Arrays.asList(0,1,2,3,4,5,13,14,22,23);
		return weather.stream().filter(e-> e.getM()>=start && e.getM()<=end &&
				(nightTariffHours.contains(e.getUTC_H()%24) || e.getD() ==6 || e.getD() ==7))
				.map(e -> e.getH()-1).collect(Collectors.toList());
	}
	public List<Integer> getG12DayTariffHoursForPeriod(int startMonth, int endMonth){
		int start = startMonth;
		int end = endMonth;
		List<Integer> nightTariffHours= Arrays.asList(0,1,2,3,4,5,13,14,22,23);
		return weather.stream().filter(e-> e.getM()>=start && e.getM()<=end &&!nightTariffHours.contains(e.getUTC_H()%24)).map(e -> e.getH()-1).collect(Collectors.toList());
	}
	
	public List<Integer> getG12wDayTariffHoursForPeriod(int startMonth, int endMonth){
		int start = startMonth;
		int end = endMonth;
		List<Integer> nightTariffHours= Arrays.asList(0,1,2,3,4,5,13,14,22,23);
		return weather.stream().filter(e-> e.getM()>=start && e.getM()<=end &&
				!(nightTariffHours.contains(e.getUTC_H()%24) || e.getD() ==6 || e.getD() ==7))
				.map(e -> e.getH()-1).collect(Collectors.toList());
	}

}
