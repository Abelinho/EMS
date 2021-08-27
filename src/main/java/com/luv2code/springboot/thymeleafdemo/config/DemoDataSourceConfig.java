package com.luv2code.springboot.thymeleafdemo.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class DemoDataSourceConfig {
	@Primary
	@Bean
	@ConfigurationProperties(prefix="app.datasource")
	public DataSource appDataSource() {
		return DataSourceBuilder.create().build();
	}

	/*@ConfigurationProperties annotation 
	 * allows us to map the resource files 
	 * such as properties or YAML files to Java Bean object. 
	 * */
	@Bean
	@ConfigurationProperties(prefix="spring.data.jpa.entity")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource appDataSource) {
		return builder
				.dataSource(appDataSource)
				.build();
	}

	@Bean
	@ConfigurationProperties(prefix="security.datasource")
	public DataSource securityDataSource() {
		return DataSourceBuilder.create().build();
	}
}

