package org.kulig.renewableenergy.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages={"org.kulig.renewableenergy.repository.springdatajpa"})
@EnableTransactionManagement
public class DatabaseConfig {
	
	@Bean
	public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/RENEWABLE_ENERGY_DATABASE?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC&amp;autoReconnect=true&amp;useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("qwas");
        return dataSource;
	}
	
	private Properties additionalProperties() {
		 Properties properties = new Properties();
	     properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	     properties.setProperty("hibernate.show_sql", "false");
	     return properties;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setJpaVendorAdapter(vendorAdapter);
	    factory.setPackagesToScan("org.kulig.renewableenergy.model.entities");
	    factory.setDataSource(dataSource());
	    factory.setJpaProperties(additionalProperties());
	    return factory;
	}
	
	  @Bean
	  public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(emf);
	    return txManager;
	  }
	
}
