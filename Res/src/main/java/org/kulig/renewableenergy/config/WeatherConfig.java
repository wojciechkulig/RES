package org.kulig.renewableenergy.config;

import java.util.List;

import org.kulig.renewableenergy.model.entities.City;
import org.kulig.renewableenergy.model.entities.Weather;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class WeatherConfig {
	
	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public List<Weather> weather(City city){
		return city.getWeather();
	}

}
