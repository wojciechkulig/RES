package org.kulig.renewableenergy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PvModuleDegradationConfig {

	@Bean
	public int getDegradationYear(){
		return 0;
	}

}
