package org.kulig.renewableenergy.config;

import java.util.List;

import org.kulig.renewableenergy.model.entities.HourlyEnergyConsumption;
import org.kulig.renewableenergy.model.entities.TariffGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class HourlyEnergyConsumptionConfig {
	
	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public List<HourlyEnergyConsumption> hourlyEnergyConsumption(TariffGroup tariffGroup){
		return tariffGroup.getHourlyEnergyConsumption();
	}

}
