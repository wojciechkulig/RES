package org.kulig.renewableenergy.config;

import java.util.ArrayList;
import java.util.List;

import org.kulig.renewableenergy.model.entities.PvSystemEnergyBilance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PvSystemEnergyBilanceConfig {
	@Bean(name="bilance")
	public List<List<PvSystemEnergyBilance>> getPvSystemEnergyDistribution(){
		return new ArrayList<List<PvSystemEnergyBilance>>();
	}
}
