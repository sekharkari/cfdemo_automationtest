package com.mycf.springcloud.automation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.mycf.springcloud.automation")
@PropertySource("classpath:springconfig.properties")
public class AppConfig {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
	
}
